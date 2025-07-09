package com.mero.mero_backend.controller;

import com.mero.mero_backend.domain.entity.CompanyInfo;
import com.mero.mero_backend.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
@RequiredArgsConstructor

public class CompanyController {
    private final CompanyService companyInfoService;

    @GetMapping("/checkLoginIdDuplicate")
    public int checkLoginId(@RequestParam("loginId") String loginId) {
        return companyInfoService.checkLoginIdDuplicate(loginId);
    }

    @PostMapping("/insertCompanyInfo")
    public ResponseEntity<CompanyInfo> insertCompanyInfo(@RequestBody CompanyInfo companyInfo) {
        CompanyInfo createdCompanyInfo = companyInfoService.insertCompanyInfo(companyInfo);
        return ResponseEntity.ok(createdCompanyInfo);
    }

    @GetMapping("/getCompanyInfoList")
    public List<CompanyInfo> getCompanyInfoList() {
        return companyInfoService.getCompanyListInfo();
    }

    @GetMapping("/getCompanyInfo")
    public CompanyInfo getCompanyInfo(@RequestParam("companyId") String companyId) {
        return companyInfoService.getCompanyInfo(companyId);
    }
}
