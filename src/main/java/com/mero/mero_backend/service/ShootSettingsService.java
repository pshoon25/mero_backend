package com.mero.mero_backend.service;

import com.mero.mero_backend.domain.entity.ShootSettings;
import com.mero.mero_backend.domain.entity.FrameManagement;
import com.mero.mero_backend.repository.ShootSettingsRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.mero.mero_backend.service.FrameService;

import java.io.IOException;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class ShootSettingsService {
    private final ShootSettingsRepository shootSettingsRepository;
    private final FrameService frameService;

    @Transactional
    public ShootSettings getShootSetting(String companyId, String frameId) {
        FrameManagement frameManagement = frameService.checkExistenceFrameManagement(frameId, companyId);
        ShootSettings result = shootSettingsRepository.findByCompanyIdAndFrameId(frameManagement.getCompanyId(), frameManagement.getFrameId());
        if (result == null) {
            ShootSettings commonSettings = shootSettingsRepository.findByCompanyIdAndFrameId("common", frameId);
            commonSettings.setSettingId(generateSettingId());
            commonSettings.setCompanyId(companyId);
            return shootSettingsRepository.save(commonSettings);
        } else {
           return result;
        }
    }  

    public String generateSettingId() {
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        int maxId = shootSettingsRepository.findMaxIdByDate(today);
        String formattedId = String.format("%02d", maxId + 1);
        return today + formattedId;
    }
}
