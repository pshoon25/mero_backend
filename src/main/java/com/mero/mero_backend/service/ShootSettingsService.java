package com.mero.mero_backend.service;

import com.mero.mero_backend.domain.entity.ShootSettings;
import com.mero.mero_backend.repository.ShootSettingsRepository;

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
public class ShootSettingsService {
    private final ShootSettingsRepository shootSettingsRepository;

    @Transactional
    public ShootSettings getShootSetting(String companyId, String frameId) {
        ShootSettings result = shootSettingsRepository.findByCompanyIdAndFrameId(companyId, frameId);
        if (result == null) {
            ShootSettings commonSettings = shootSettingsRepository.findByCompanyIdAndFrameId("COMMON", frameId);
            commonSettings.setSettingId(generateSettingId());
            commonSettings.setCompanyId(companyId);
            return shootSettingRepository.save(commonSettings);
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
