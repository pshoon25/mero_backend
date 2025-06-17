package com.mero.mero_backend.service;

import com.mero.mero_backend.domain.entity.CompanyInfo;
import com.mero.mero_backend.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;


    public CompanyInfo insertCompanyInfo(CompanyInfo companyInfo) {
        // 고유 ID를 설정 (예: YYYYMMdd + 2자리 ID 생성 로직)
        companyInfo.setCompanyId(generateCompanyInfoId());
        return companyRepository.save(companyInfo);
    }

    public List<CompanyInfo> getCompanyListInfo() {
        return companyRepository.findAll();
    }

    public String generateCompanyInfoId() {
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        int maxId = companyRepository.findMaxIdByDate(today);
        String formattedId = String.format("%02d", maxId + 1);
        return today + formattedId;
    }
}
