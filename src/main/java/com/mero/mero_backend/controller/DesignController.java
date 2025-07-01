package com.mero.mero_backend.controller;

import com.mero.mero_backend.domain.entity.DesignManagement;
import com.mero.mero_backend.service.DesignService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/design")
@RequiredArgsConstructor
@Slf4j
public class DesignController {

    private final DesignService designService;

    /**
     * 배경 이미지 업로드
     */
    @PostMapping("/background/upload")
    public ResponseEntity<Map<String, Object>> uploadBackgroundImage(
            @RequestParam("image") MultipartFile file,
            @RequestParam(value = "companyId", required = false) String companyId) {

        try {
            log.info("배경 이미지 업로드 시작 - 파일명: {}, 크기: {}", file.getOriginalFilename(), file.getSize());

            DesignManagement result = designService.uploadBackgroundImage(file, companyId);

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "배경 이미지가 성공적으로 업로드되었습니다.");
            response.put("data", result);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            log.error("배경 이미지 업로드 실패", e);

            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "이미지 업로드에 실패했습니다: " + e.getMessage());

            return ResponseEntity.badRequest().body(response);
        }
    }

    /**
     * 배경 이미지 조회
     */
    @GetMapping("/background")
    public ResponseEntity<Map<String, Object>> getBackgroundImage(
            @RequestParam(value = "companyId", required = false) String companyId) {

        try {
            DesignManagement backgroundImage = designService.getBackgroundImage(companyId);

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("data", backgroundImage);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            log.error("배경 이미지 조회 실패", e);

            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "배경 이미지 조회에 실패했습니다: " + e.getMessage());

            return ResponseEntity.badRequest().body(response);
        }
    }

    /**
     * 배경 이미지 파일 서빙
     */
    @GetMapping("/background/file/{designId}")
    public ResponseEntity<Resource> getBackgroundImageFile(@PathVariable String designId) {

        try {
            Resource resource = designService.getBackgroundImageFile(designId);

            // 파일 확장자에 따른 Content-Type 설정
            String contentType = "image/jpeg"; // 기본값
            String filename = resource.getFilename();
            if (filename != null) {
                if (filename.toLowerCase().endsWith(".png")) {
                    contentType = "image/png";
                } else if (filename.toLowerCase().endsWith(".gif")) {
                    contentType = "image/gif";
                }
            }

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + filename + "\"")
                    .body(resource);

        } catch (Exception e) {
            log.error("배경 이미지 파일 서빙 실패 - designId: {}", designId, e);
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * 배경 이미지 삭제
     */
    @DeleteMapping("/background/{designId}")
    public ResponseEntity<Map<String, Object>> deleteBackgroundImage(@PathVariable String designId) {

        try {
            log.info("배경 이미지 삭제 시작 - designId: {}", designId);

            designService.deleteBackgroundImage(designId);

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "배경 이미지가 성공적으로 삭제되었습니다.");

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            log.error("배경 이미지 삭제 실패 - designId: {}", designId, e);

            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "이미지 삭제에 실패했습니다: " + e.getMessage());

            return ResponseEntity.badRequest().body(response);
        }
    }

    /**
     * 배경 이미지 교체 (기존 삭제 후 새로 업로드)
     */
    @PutMapping("/background")
    public ResponseEntity<Map<String, Object>> replaceBackgroundImage(
            @RequestParam("image") MultipartFile file,
            @RequestParam(value = "companyId", required = false) String companyId) {

        try {
            log.info("배경 이미지 교체 시작 - 파일명: {}", file.getOriginalFilename());

            DesignManagement result = designService.replaceBackgroundImage(file, companyId);

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "배경 이미지가 성공적으로 교체되었습니다.");
            response.put("data", result);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            log.error("배경 이미지 교체 실패", e);

            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "이미지 교체에 실패했습니다: " + e.getMessage());

            return ResponseEntity.badRequest().body(response);
        }
    }

    /**
     * 이미지 유효성 검증 (업로드 前 체크용)
     */
    @PostMapping("/background/validate")
    public ResponseEntity<Map<String, Object>> validateBackgroundImage(
            @RequestParam("image") MultipartFile file) {

        try {
            Map<String, Object> validation = designService.validateImageFile(file);

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("validation", validation);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            log.error("이미지 유효성 검증 실패", e);

            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "이미지 유효성 검증에 실패했습니다: " + e.getMessage());

            return ResponseEntity.badRequest().body(response);
        }
    }
}