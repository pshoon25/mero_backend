package com.mero.mero_backend.controller;

import com.mero.mero_backend.domain.entity.InquiryDetails;
import com.mero.mero_backend.service.CtaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/cta")
@RequiredArgsConstructor
public class CtaController {
    private final CtaService ctaService;

    @PostMapping("/saveInquiryDetails")
    public void insertInquiryDetails(@RequestBody Map<String, Object> requestMap){
        ctaService.saveInquiryDetails(requestMap);
    }

    @GetMapping("/getInquiryDetailsList")
    public List<InquiryDetails> getInquiryDetailsList() {
        return ctaService.getInquiryDetailsList();
    }
}
