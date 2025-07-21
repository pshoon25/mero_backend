package com.mero.mero_backend.controller;

import com.mero.mero_backend.domain.dto.FrameRequestDto;
import com.mero.mero_backend.domain.dto.FrameResponseDto;
import com.mero.mero_backend.domain.dto.MediaResultsRequestDto;
import com.mero.mero_backend.domain.entity.Frame;
import com.mero.mero_backend.domain.entity.MediaResults;
import com.mero.mero_backend.service.MediaResultService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/mediaResults")
@RequiredArgsConstructor
@Slf4j
public class MediaResultsController {
    private final MediaResultService mediaResultService;

    @GetMapping("/getRecordGroup")
    public ResponseEntity<Map<String, Object>> getRecordGroup(@RequestParam("companyId") String companyId) {
        try {
            String recordGroup = mediaResultService.getRecordGroup(companyId);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("data", recordGroup);
            response.put("message", "Record Group 성공적으로 불러왔습니다.");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("프레임 목록 불러오기 실패: {}", e.getMessage());
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Record Group 불러오는데 실패했습니다: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping("/saveMediaResults")
    public ResponseEntity<Map<String, Object>> saveMediaResults(
            @RequestPart(value = "image", required = false) MultipartFile file,
            @RequestPart("mediaResultsRequestDto") MediaResultsRequestDto mediaResultsRequestDto) {
        try {
            MediaResults result = mediaResultService.saveMediaResults(file, mediaResultsRequestDto);
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
}
