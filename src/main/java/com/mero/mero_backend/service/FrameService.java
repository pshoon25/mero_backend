package com.mero.mero_backend.service;

import com.mero.mero_backend.repository.FrameRepository;
import com.mero.mero_backend.service.DesignService;
import com.mero.mero_backend.domain.entity.Frame;
import com.mero.mero_backend.domain.entity.DesignManagement;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FrameService {
    private final FrameRepository frameRepository;
    private final DesignService designService;

    @Transactional
    public void saveFrame(MultipartFile file, Map<String, Object> requestMap) {
        String companyId = String.valueOf(requestMap.get("companyId"));
        DesignManagement designManagement = designService.uploadImage(file, companyId, "FRAME", null);

        Frame frame = new Frame();
        String frameId = generateFrameId();
        frame.setFrameId(frameId);
        frame.setDesignId(designManagement.getDesignId);
        frame.setFrameName(String.valueOf(requsetMap.get("frameName")));
        frame.setUseYn(String.valueOf(requsetMap.get("useYn")));
        frameRepository.save(frame);
    }   

    public String generateFrameId() {
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        int maxId = frameRepository.findMaxIdByDate(today);
        String formattedId = String.format("%02d", maxId + 1);
        return today + formattedId;
    }
}
