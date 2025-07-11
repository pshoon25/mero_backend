package com.mero.mero_backend.service;

import com.mero.mero_backend.domain.dto.FrameDesignRequestDto;
import com.mero.mero_backend.domain.dto.FrameDesignResponseDto;
import com.mero.mero_backend.domain.entity.FrameManagement;
import com.mero.mero_backend.repository.FrameManagementRepository;
import com.mero.mero_backend.repository.FrameRepository;
import com.mero.mero_backend.domain.entity.Frame;
import com.mero.mero_backend.domain.dto.FrameRequestDto;
import com.mero.mero_backend.domain.dto.FrameResponseDto;
import com.mero.mero_backend.domain.entity.DesignManagement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class FrameService {
    private final FrameRepository frameRepository;
    private final FrameManagementRepository frameManagementRepository;
    private final DesignService designService;

    @Transactional
    public Frame saveFrame(MultipartFile file, FrameRequestDto frameRequestDto) {
        DesignManagement designManagement;
        try {
            designManagement = designService.uploadImage(file, frameRequestDto.getCompanyId(), frameRequestDto.getApplicationType(), null);
        } catch (IOException e) {
            throw new RuntimeException("프레임 이미지 업로드에 실패했습니다.", e);
        }

        Frame frame = new Frame();
        String frameId = generateFrameId();
        frame.setFrameId(frameId);
        frame.setDesignId(designManagement.getDesignId());
        frame.setFrameName(frameRequestDto.getFrameName());
        frame.setUseYn(frameRequestDto.getUseYn());
        return frameRepository.save(frame);
    }  

    public List<FrameResponseDto> getFrames() {
        return frameRepository.getAllFramesWithDesignInfo();
    }

    public List<FrameDesignResponseDto> getFrameDesigns(String frameId, String companyId) {
        return frameManagementRepository.findByFrameIdAndCompanyId(frameId, companyId);
    }

    @Transactional
    public FrameManagement saveFrameDesign(MultipartFile file, FrameDesignRequestDto frameDesignRequestDto) {
        DesignManagement designManagement;
        try {
            designManagement = designService.uploadImage(file, frameDesignRequestDto.getCompanyId(), frameDesignRequestDto.getApplicationType(), null);
        } catch (IOException e) {
            throw new RuntimeException("프레임 디자인 이미지 업로드에 실패했습니다.", e);
        }

        FrameManagement frameManagement = new FrameManagement();
        String frameMngId = generateFrameMngId();
        frameManagement.setFrameMngId(frameMngId);
        frameManagement.setFrameId(frameDesignRequestDto.getFrameId());
        frameManagement.setCompanyId(frameDesignRequestDto.getCompanyId());
        frameManagement.setUseYn(frameDesignRequestDto.getUseYn());
        return frameManagementRepository.save(frameManagement);
    }

    public String generateFrameId() {
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        int maxId = frameRepository.findMaxIdByDate(today);
        String formattedId = String.format("%02d", maxId + 1);
        return today + formattedId;
    }

    public String generateFrameMngId() {
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        int maxId = frameManagementRepository.findMaxIdByDate(today);
        String formattedId = String.format("%02d", maxId + 1);
        return today + formattedId;
    }
}
