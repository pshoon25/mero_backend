package com.mero.mero_backend.controller;

import com.mero.mero_backend.domain.dto.*;
import com.mero.mero_backend.domain.entity.Frame;
import com.mero.mero_backend.domain.entity.FrameManagement;
import com.mero.mero_backend.domain.entity.DesignManagement;

import com.mero.mero_backend.service.FrameService;

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
@RequestMapping("/frame")
@RequiredArgsConstructor
@Slf4j
public class FrameController {

    private final FrameService frameService;

    @PostMapping("/saveFrame")
    public ResponseEntity<Map<String, Object>> saveFrame(
            @RequestPart(value = "image", required = false) MultipartFile file,
            @RequestPart("frameRequestDto") FrameRequestDto frameRequestDto) {
        try {
            Frame result = frameService.saveFrame(file, frameRequestDto);
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
    public ResponseEntity<Map<String, Object>> getFrames(@RequestParam("companyId") String companyId) {
        try {
            List<FrameResponseDto> frames = frameService.getFrames(companyId); 
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

    @GetMapping("/getFrameDesigns")
    public ResponseEntity<Map<String, Object>> getFrameDesigns(@RequestParam("frameId") String frameId,
                                                               @RequestParam("companyId") String companyId) {
        try {
            List<FrameDesignResponseDto> frames = frameService.getFrameDesigns(frameId, companyId);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("data", frames);
            response.put("message", "프레임 목록을 성공적으로 불러왔습니다.");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("프레임 디자인 불러오기 실패: {}", e.getMessage());
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "프레임 디자인을 불러오는데 실패했습니다: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping("/saveFrameDesign")
    public ResponseEntity<Map<String, Object>> saveFrameDesign(
            @RequestPart(value = "image", required = false) MultipartFile file,
            @RequestPart("frameDesignRequestDto") FrameDesignRequestDto frameDesignRequestDto) {
        try {
            DesignManagement result = frameService.saveFrameDesign(file, frameDesignRequestDto);
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

    @GetMapping("/app/getFrames")
    public ResponseEntity<Map<String, Object>> getAppFrames(@RequestParam("companyId") String companyId) {
        try {
            List<FrameResponseDto> frames = frameService.getAppFrames(companyId);
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

    @PutMapping("/updateFrameMngUseYn")
    public ResponseEntity<Map<String, Object>> updateFrameMngUseYn(@RequestParam("frameId") String frameId, 
                                                                   @RequestParam("companyId") String companyId,
                                                                   @RequestParam("useYn") String useYn) {
        try {
            FrameManagementResponseDto result = frameService.updateFrameMngUseYn(frameId, companyId, useYn);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "사용 여부 변경이 성공적으로 완료되었습니다.");
            response.put("data", result);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("사용 여부 변경 실패", e);
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "사용 여부 변경이 실패했습니다: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/getFrameDesignsAndSettings")
    public ResponseEntity<Map<String, Object>> getFrameDesignsAndSettings(@RequestParam("frameId") String frameId,
                                                                          @RequestParam("companyId") String companyId) {
        try {
            List<FrameDesignAndSettingsResponseDto> frames = frameService.getFrameDesignsAndSettings(frameId, companyId);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("data", frames);
            response.put("message", "프레임 목록을 성공적으로 불러왔습니다.");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("프레임 디자인 불러오기 실패: {}", e.getMessage());
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "프레임 디자인을 불러오는데 실패했습니다: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
