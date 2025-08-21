package com.mero.mero_backend.service;

import com.mero.mero_backend.converter.Encrypt;
import com.mero.mero_backend.domain.entity.CompanyInfo;
import com.mero.mero_backend.domain.entity.CompanySalt;
import com.mero.mero_backend.repository.CompanyRepository;
import com.mero.mero_backend.repository.CompanySaltRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final Encrypt encrypt;
    private final CompanyRepository companyRepository;
    private final CompanySaltRepository companySaltRepository;

    public int checkLoginIdDuplicate(String loginId) {
        // 로그인 ID 조회
        CompanyInfo companyInfo = companyRepository.findByLoginId(loginId);

        // 로그인 ID가 존재하면 1, 존재하지 않으면 0 반환
        return (companyInfo != null) ? 1 : 0;
    }

    @Transactional
    public CompanyInfo insertCompanyInfo(CompanyInfo companyInfo) {
        // 비밀번호 암호화
        String salt = encrypt.getSalt();
        String encodedPw = encrypt.getEncrypt(String.valueOf(companyInfo.getLoginPw()), salt);

        // 고유 ID를 설정 (예: YYYYMMdd + 2자리 ID 생성 로직)
        String companyId = generateCompanyInfoId();
        companyInfo.setCompanyId(companyId);
        companyInfo.setLoginPw(encodedPw);
        companyInfo.setCompanyType("USER");
        companyInfo.setUseYn("Y");
        companyInfo.setRegisterDateTime(LocalDate.now());
        CompanyInfo result = companyRepository.save(companyInfo);
        
        // salt 정보 저장
        CompanySalt companySalt = new CompanySalt();
        companySalt.setCompanyId(companyId);
        companySalt.setSalt(salt);
        companySaltRepository.save(companySalt);
        
        return result;
    }

    public List<CompanyInfo> getCompanyListInfo() {
        return companyRepository.findAll();
    }

    public CompanyInfo getCompanyInfo(String companyId) {
        return companyRepository.findByCompanyId(companyId);
    }

    @Transactional
    public int updateUseYnByCompanyId(String companyId, String useYn) {
        return companyRepository.updateUseYnByCompanyId(companyId, useYn);
    }

    public String generateCompanyInfoId() {
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        int maxId = companyRepository.findMaxIdByDate(today);
        String formattedId = String.format("%02d", maxId + 1);
        return today + formattedId;
    }
}
