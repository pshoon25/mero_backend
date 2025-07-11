package com.mero.mero_backend.service;

import com.mero.mero_backend.domain.entity.DesignManagement;
import com.mero.mero_backend.repository.DesignRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.lang.Nullable;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class DesignService {

    private final DesignRepository designRepository;

    @Value("${file.upload-dir:/app/designImage/}")
    private String UPLOAD_DIR;

    private static final long MAX_FILE_SIZE = 5 * 1024 * 1024; // 5MB
    private static final String[] ALLOWED_EXTENSIONS = {".jpg", ".jpeg", ".png", ".gif"};

    @PostConstruct
    public void init() {
        try {
            Path uploadDir = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadDir)) {
                Files.createDirectories(uploadDir);
                log.info("Created upload directory: {}", UPLOAD_DIR);
            }
            // Set directory permissions to 755 (rwxr-xr-x)
            Files.setPosixFilePermissions(uploadDir, PosixFilePermissions.fromString("rwxr-xr-x"));
        } catch (IOException e) {
            log.error("Failed to create or set permissions for upload directory: {}", UPLOAD_DIR, e);
        }
    }

    public Map<String, Object> validateImageFile(MultipartFile file) {
        Map<String, Object> validation = new HashMap<>();

        // Check file size
        if (file.getSize() > MAX_FILE_SIZE) {
            validation.put("valid", false);
            validation.put("message", "File size exceeds 5MB limit");
            return validation;
        }

        // Check file type
        String filename = file.getOriginalFilename();
        if (filename == null || !isValidExtension(filename)) {
            validation.put("valid", false);
            validation.put("message", "Invalid file type. Only JPG, PNG, and GIF are allowed");
            return validation;
        }

        validation.put("valid", true);
        return validation;
    }

    public DesignManagement uploadImage(MultipartFile file, String companyId, String applicationType,  @Nullable String frameMngId) throws IOException {
        // Validate file
        Map<String, Object> validation = validateImageFile(file);
        if (!(Boolean) validation.get("valid")) {
            throw new IllegalArgumentException((String) validation.get("message"));
        }

        // Path 설정
        Path companyDirPath;
        if ("FRAME".equals(applicationType) {
            companyDirPath= Paths.get(UPLOAD_DIR, "common");
        } else {
            companyDirPath= Paths.get(UPLOAD_DIR, companyId);
        }
        Path targetDirPath = Paths.get(companyDirPath.toString(), applicationType.toLowerCase());

        // 폴더가 없으면 생성 및 권한 설정
        if (!Files.exists(targetDirPath)) {
            Files.createDirectories(targetDirPath);
            log.info("Created directory: {}", targetDirPath);
            Set<PosixFilePermission> dirPerms = PosixFilePermissions.fromString("rwxr-xr-x");
            Files.setPosixFilePermissions(targetDirPath, dirPerms);
        }

        String uuid = UUID.randomUUID().toString();
        String originalFilename = Objects.requireNonNull(file.getOriginalFilename());
        String filename = uuid + getFileExtension(originalFilename);
        Path filePath = targetDirPath.resolve(filename);

        // 파일 저장 및 권한 설정
        Files.write(filePath, file.getBytes());
        Set<java.nio.file.attribute.PosixFilePermission> filePerms = PosixFilePermissions.fromString("rw-r--r--");
        Files.setPosixFilePermissions(filePath, filePerms);

        // Get image dimensions
        BufferedImage image = ImageIO.read(file.getInputStream());

        String designId = generateDesignId();

        // Create and save entity
        DesignManagement designManagement = new DesignManagement();
        designManagement.setDesignId(designId);
        designManagement.setDesignImageUrl(applicationType.toLowerCase() + designId);
        designManagement.setCompanyId(companyId);
        designManagement.setFileName(filename);
        designManagement.setFileSize(file.getSize());
        designManagement.setImageWidth(image.getWidth());
        designManagement.setImageHeight(image.getHeight());
        designManagement.setApplicationType(applicationType.toUpperCase());
        designManagement.setFrameMngId("FRAME_DESIGN".equals(String.valueOf(applicationType.toUpperCase())) ? frameMngId : "");
        designManagement.setUploadDate(LocalDateTime.now());

        designRepository.save(designManagement);

        return designManagement;
    }

    public DesignManagement getImage(String companyId, String applicationType,  @Nullable String frameMngId) {
        if ("FRAME_DESIGN".equals(String.valueOf(applicationType.toUpperCase()))) {
            return designRepository.findByCompanyIdAndApplicationTypeAndFrameMngId(companyId, applicationType, frameMngId).orElse(null);
        } else {
            return designRepository.findByCompanyIdAndApplicationType(companyId, applicationType).orElse(null);
        }
    }

    public Resource getImageFile(String designId) {
        try {
            DesignManagement design = designRepository.findById(Long.parseLong(designId)).orElseThrow(() -> new RuntimeException("Design not found"));
            Path filePath = Paths.get(UPLOAD_DIR, design.getCompanyId(), design.getApplicationType().toLowerCase(), design.getFileName());
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists() && resource.isReadable()) {
                return resource;
            }
        } catch (Exception e) {
            log.error("Error loading image file", e);
        }
        throw new RuntimeException("File not found");
    }

    public void deleteImage(String designId) throws IOException {
        DesignManagement design = designRepository.findById(Long.parseLong(designId)).orElseThrow(() -> new RuntimeException("Design not found"));

        Path filePath = Paths.get(UPLOAD_DIR).resolve(design.getFileName());
        if (Files.exists(filePath)) {
            Files.delete(filePath);
            designRepository.deleteById(Long.parseLong(designId));
        } else {
            throw new RuntimeException("File not found");
        }
    }

    public DesignManagement replaceImage(MultipartFile file, String companyId, String applicationType,  @Nullable String frameMngId) throws IOException {
        // Delete existing image for the company
        DesignManagement existingDesign = designRepository.findByCompanyIdAndApplicationType(companyId, applicationType).orElse(null);
        if (existingDesign != null) {
            deleteImage(existingDesign.getDesignId());
        }

        // Upload new image
        if ("FRAME_DESIGN".equals(String.valueOf(applicationType.toUpperCase()))) {
            return uploadImage(file, companyId, applicationType, frameMngId);
        } else {
            return uploadImage(file, companyId, applicationType, null);
        }
    }

    private boolean isValidExtension(String filename) {
        for (String ext : ALLOWED_EXTENSIONS) {
            if (filename.toLowerCase().endsWith(ext)) {
                return true;
            }
        }
        return false;
    }

    private String getFileExtension(String filename) {
        if (filename == null) return ".jpg";
        int lastIndex = filename.lastIndexOf('.');
        return lastIndex == -1 ? ".jpg" : filename.substring(lastIndex);
    }

    public String generateDesignId() {
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        int maxId = designRepository.findMaxIdByDate(today);
        String formattedId = String.format("%02d", maxId + 1);
        return today + formattedId;
    }
}
