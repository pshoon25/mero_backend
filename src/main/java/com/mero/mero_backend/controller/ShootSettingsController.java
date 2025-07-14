package com.mero.mero_backend.controller;

import com.mero.mero_backend.domain.entity.ShootSettings;
import com.mero.mero_backend.service.ShootSettingsService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestPart;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/setting")
@RequiredArgsConstructor
@Slf4j
public class ShootSettingsController {

    private final ShootSettingsService shootSettingsService;

    @GetMapping("/getShootSetting")
    public ResponseEntity<Map<String, Object>> getShootSetting(@RequestParam("frameId") String frameId,
                                                               @RequestParam("companyId") String companyId) {
        try {
            ShootSettings result = shootSettingsService.getShootSetting(frameId, companyId); 
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("data", result);
            response.put("message", "촬영 설정 값을 성공적으로 불러왔습니다.");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("촬영 설정 값 불러오기 실패: {}", e.getMessage());
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "촬영 설정 값을 불러오는데 실패했습니다: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
