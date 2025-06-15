package com.mero.mero_backend.service;

import com.mero.mero_backend.domain.entity.InquiryDetails;
import com.mero.mero_backend.repository.CtaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CtaService {

    private final CtaRepository ctaRepository;

    public void saveInquiryDetails(Map<String, Object> requestMap) { // String을 Date로 변환
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // 문자열을 LocalDate로 변환
        String rentalStartDateStr = (String) requestMap.getOrDefault("rentalStartDate", "");
        String rentalEndDateStr = (String) requestMap.getOrDefault("rentalEndDate", "");

        LocalDate localRentalStartDate = null;
        LocalDate localRentalEndDate = null;

        if (!rentalStartDateStr.isEmpty()) {
            localRentalStartDate = LocalDate.parse(rentalStartDateStr, formatter);
        }
        if (!rentalEndDateStr.isEmpty()) {
            localRentalEndDate = LocalDate.parse(rentalEndDateStr, formatter);
        }

        // LocalDate를 Date로 변환
        Date rentalStartDate = localRentalStartDate != null
                ? Date.from(localRentalStartDate.atStartOfDay(ZoneId.systemDefault()).toInstant())
                : null;

        Date rentalEndDate = localRentalEndDate != null
                ? Date.from(localRentalEndDate.atStartOfDay(ZoneId.systemDefault()).toInstant())
                : null;

        // InquiryDetails 객체 생성 및 값 설정
        InquiryDetails inquiryDetails = new InquiryDetails();
        Long inquiryId = ctaRepository.findMaxInquiryId();
        inquiryDetails.setInquiryId(inquiryId);
        inquiryDetails.setCompany(String.valueOf(requestMap.get("company")));
        inquiryDetails.setContactNumber(String.valueOf(requestMap.get("contactNumber")));
        inquiryDetails.setRentalStartDate(rentalStartDate);
        inquiryDetails.setRentalEndDate(rentalEndDate);
        inquiryDetails.setAddress(String.valueOf(requestMap.get("address")));
        inquiryDetails.setInquiryDateTime(LocalDateTime.now());

        // 저장
        ctaRepository.save(inquiryDetails);
    }
}
