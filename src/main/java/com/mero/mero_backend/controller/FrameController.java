package com.mero.mero_backend.controller;

import com.mero.mero_backend.domain.entity.Frame;
import com.mero.mero_backend.domain.dto.FrameRequest;
import com.mero.mero_backend.service.FrameService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestPart;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/frame")
@RequiredArgsConstructor
@Slf4j
public class FrameController {

    private final FrameService frameService;

    @PostMapping("/saveFrame")
    public ResponseEntity<Map<String, Object>> saveFrame(
            @RequestPart(value = "image", required = false) MultipartFile file,
            @RequestPart("frameRequest") FrameRequest frameRequest) {
        try {
            Frame result = frameService.saveFrame(file, frameRequest);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "저장이 성공적으로 완료되었습니다.");
            response.put("data", result);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("저장 실패", e);
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "저장에 실패했습니다: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/getFrames")
    public ResponseEntity<Map<String, Object>> getFrames() {
        try {
            List<Frame> frames = frameService.getFrames(); 
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("data", frames);
            response.put("message", "프레임 목록을 성공적으로 불러왔습니다.");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("프레임 목록 불러오기 실패: {}", e.getMessage());
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "프레임 목록을 불러오는데 실패했습니다: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
