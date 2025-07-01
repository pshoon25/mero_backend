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
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CtaService {

    private final CtaRepository ctaRepository;

    public void saveInquiryDetails(Map<String, Object> requestMap) {// String을 Date로 변환
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Date rentalStartDate = new Date();
        Date rentalEndDate = new Date();

        // 문자열을 LocalDate로 변환
        LocalDate localRentalStartDate = LocalDate.parse(String.valueOf(requestMap.getOrDefault("rentalStartDate", "")), formatter);
        LocalDate localRentalEndDate = LocalDate.parse(String.valueOf(requestMap.getOrDefault("rentalEndDate", "")), formatter);

        // LocalDate를 Date로 변환
        rentalStartDate = Date.from(localRentalStartDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        rentalEndDate = Date.from(localRentalEndDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        InquiryDetails inquiryDetails = new InquiryDetails();
        Long inquiryId = ctaRepository.findMaxInquiryId();
        inquiryDetails.setInquiryId(inquiryId);
        inquiryDetails.setCompany(String.valueOf(requestMap.get("company")));
        inquiryDetails.setContactNumber(String.valueOf(requestMap.get("contactNumber")));
        inquiryDetails.setRentalStartDate(rentalStartDate);
        inquiryDetails.setRentalEndDate(rentalEndDate);
        inquiryDetails.setAddress(String.valueOf(requestMap.get("address")));
        inquiryDetails.setAddress(String.valueOf(requestMap.get("intendedUse")));
        inquiryDetails.setInquiryDateTime(LocalDateTime.now());

        ctaRepository.save(inquiryDetails);
    }

    public List<InquiryDetails> getInquiryDetailsList() {
        return ctaRepository.findAll();
    }
}
