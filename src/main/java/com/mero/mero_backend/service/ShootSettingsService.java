package com.mero.mero_backend.service;

import com.mero.mero_backend.domain.entity.ShootSetting;
import com.mero.mero_backend.repository.ShootSettingRepository;

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
    private final ShootSettingRepository shootSettingRepository;

    @Transactional
    public ShootSetting getShootSetting(String companyId, String frameId) {
        ShootSetting result = shootSettingRepository.findByCompanyIdAndFrameId(companyId, frameId);
        if (result == null) {
            ShootSetting commonSettings = shootSettingRepository.findByCompanyIdAndFrameId("COMMON", frameId);
            commonSettings.setSettingId(generateSettingId());
            commonSettings.setCompanyId(companyId);
            return shootSettingRepository.save(commonSettings);
        } else {
           return result;
        }
    }  

    public String generateSettingId() {
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        int maxId = shootSettingRepository.findMaxIdByDate(today);
        String formattedId = String.format("%02d", maxId + 1);
        return today + formattedId;
    }
}
