package com.mero.mero_backend.service;

import com.mero.mero_backend.repository.FrameRepository;
import com.mero.mero_backend.service.DesignService;
import com.mero.mero_backend.domain.entity.Frame;
import com.mero.mero_backend.domain.dto.FrameRequest;
import com.mero.mero_backend.domain.entity.DesignManagement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
@Slf4j
public class FrameService {
    private final FrameRepository frameRepository;
    private final DesignService designService;

    @Transactional
    public Frame saveFrame(MultipartFile file, FrameRequest frameRequest) {
        DesignManagement designManagement;
        try {
            designManagement = designService.uploadImage(file, frameRequest.getCompanyId(), frameRequest.getApplicationType(), null);
        } catch (IOException e) {
            log.error("Failed to upload image for frame: {}", e.getMessage());
            throw new RuntimeException("프레임 이미지 업로드에 실패했습니다.", e);
        }

        Frame frame = new Frame();
        String frameId = generateFrameId();
        frame.setFrameId(frameId);
        frame.setDesignId(designManagement.getDesignId());
        frame.setFrameName(frameRequest.getFrameName());
        frame.setUseYn(frameRequest.getUseYn());
        return frameRepository.save(frame);
    }   

    public String generateFrameId() {
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        int maxId = frameRepository.findMaxIdByDate(today);
        String formattedId = String.format("%02d", maxId + 1);
        return today + formattedId;
    }
}
