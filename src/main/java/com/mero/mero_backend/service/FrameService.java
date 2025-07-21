package com.mero.mero_backend.service;

import com.mero.mero_backend.domain.dto.*;
import com.mero.mero_backend.domain.entity.FrameManagement;
import com.mero.mero_backend.repository.FrameManagementRepository;
import com.mero.mero_backend.repository.FrameRepository;
import com.mero.mero_backend.repository.DesignRepository;
import com.mero.mero_backend.domain.entity.Frame;
import com.mero.mero_backend.domain.entity.DesignManagement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class FrameService {
    private final FrameRepository frameRepository;
    private final FrameManagementRepository frameManagementRepository;
    private final DesignRepository designRepository;
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
        frame.setFrameWidth(frameRequestDto.getFrameWidth());
        frame.setFrameHeight(frameRequestDto.getFrameHeight());
        frame.setFrameCellCnt(frameRequestDto.getFrameCellCnt());
        frame.setCellWidth(frameRequestDto.getCellWidth());
        frame.setCellHeight(frameRequestDto.getCellHeight());
        frame.setBorderTop(frameRequestDto.getBorderTop());
        frame.setBorderBottom(frameRequestDto.getBorderBottom());
        frame.setBorderLeft(frameRequestDto.getBorderLeft());
        frame.setBorderRight(frameRequestDto.getBorderRight());
        frame.setCrossbarHorizontal(frameRequestDto.getCrossbarHorizontal());
        frame.setCrossbarVertical(frameRequestDto.getCrossbarVertical());
        frame.setUseYn(frameRequestDto.getUseYn());
        return frameRepository.save(frame);
    }  

    public List<FrameResponseDto> getFrames(String companyId) {
        return frameRepository.getAllFramesWithDesignInfoByCompanyId(companyId);
    }

    public List<FrameDesignResponseDto> getFrameDesigns(String frameId, String companyId) {
        FrameManagement checkFrameMng = checkExistenceFrameManagement(frameId, companyId);
        return designRepository.findFrameDesignByFrameIdAndCompanyId(checkFrameMng.getFrameId(), checkFrameMng.getCompanyId());
    }

    @Transactional
    public DesignManagement saveFrameDesign(MultipartFile file, FrameDesignRequestDto frameDesignRequestDto) {
        Optional<FrameManagement> optionalFrameManagement = frameManagementRepository.findByFrameIdAndCompanyId(frameDesignRequestDto.getFrameId(), frameDesignRequestDto.getCompanyId());
        String frameMngId = optionalFrameManagement.get().getFrameMngId();
        try {
            return designService.uploadImage(file, frameDesignRequestDto.getCompanyId(), frameDesignRequestDto.getApplicationType(), frameMngId);
        } catch (IOException e) {
            throw new RuntimeException("프레임 디자인 이미지 업로드에 실패했습니다.", e);
        }
    }
    
    public List<FrameResponseDto> getAppFrames(String companyId) {
        return frameRepository.getAllFramesWithDesignInfoByCompanyIdAndUseYn(companyId);
    }

    public FrameManagementResponseDto updateFrameMngUseYn(String frameId, String companyId, String useYn) {
        FrameManagement checkFrameMng = checkExistenceFrameManagement(frameId, companyId);
        checkFrameMng.setUseYn(useYn);
        FrameManagement result = frameManagementRepository.save(checkFrameMng);
        return new FrameManagementResponseDto(result);
    }

    public List<FrameDesignAndSettingsResponseDto> getFrameDesignsAndSettings(String frameId, String companyId) {
        return frameManagementRepository.getFrameDesignAndSettingsByCompanyIdAndFrameId(frameId, companyId);
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

    @Transactional
    public FrameManagement checkExistenceFrameManagement(String frameId, String companyId) {
        Optional<FrameManagement> optionalResult = frameManagementRepository.findByFrameIdAndCompanyId(frameId, companyId);
        if (optionalResult.isEmpty()) { 
            FrameManagement frameManagement = new FrameManagement();
            frameManagement.setFrameMngId(generateFrameMngId());
            frameManagement.setFrameId(frameId);
            frameManagement.setCompanyId(companyId);
            frameManagement.setUseYn("N");
            return frameManagementRepository.save(frameManagement);
        } else {
            return optionalResult.get();
        }
    }
}
