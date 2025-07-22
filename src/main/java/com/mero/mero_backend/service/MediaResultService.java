package com.mero.mero_backend.service;

import com.mero.mero_backend.domain.dto.*;
import com.mero.mero_backend.repository.MediaResultsRepository;
import com.mero.mero_backend.domain.entity.MediaResults;
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
public class MediaResultService {
    private final MediaResultsRepository mediaResultsRepository;
    private final DesignService designService;

    public String getRecordGroup(String companyId) {
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        int maxId = mediaResultsRepository.getRecordGroup(today, companyId);
        String formattedId = String.format("%04d", maxId + 1);
        return today + "-" + formattedId;
    }

    @Transactional
    public MediaResults saveMediaResults(MultipartFile file, MediaResultsRequestDto mediaResultsRequestDto) {
        DesignManagement designManagement;
        try {
            designManagement = designService.uploadImage(file, mediaResultsRequestDto.getCompanyId(), mediaResultsRequestDto.getApplicationType(), null);
        } catch (IOException e) {
            throw new RuntimeException("미디어 결과물 업로드에 실패했습니다.", e);
        }

        MediaResults mediaResults = new MediaResults();
        String resultId = generateResultId(mediaResultsRequestDto.getCompanyId());
        mediaResults.setResultId(resultId);
        mediaResults.setCompanyId(mediaResultsRequestDto.getCompanyId());
        mediaResults.setFrameMngId(mediaResultsRequestDto.getFrameMngId());
        mediaResults.setDesignId(designManagement.getDesignId());
        mediaResults.setRecordGroup(mediaResultsRequestDto.getRecordGroup());
        mediaResults.setType(mediaResultsRequestDto.getType());
        mediaResults.setRecordDateTime(LocalDate.now());
        return mediaResultsRepository.save(mediaResults);
    }

    public List<MedaiResultsResponseDto> getMediaResults(String companyId) {
        return mediaResultsRepository.findAllByCompanyId(companyId);
    }

    public String generateResultId(String companyId) {
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        int maxSequence = mediaResultsRepository.findMaxSequenceIdByCompanyIdAndDate(companyId, today);
        String formattedSequence = String.format("%04d", maxSequence + 1);
        return companyId + "-" + today + "-" + formattedSequence;
    }
}
