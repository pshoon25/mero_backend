package com.mero.mero_backend.controller;

import com.mero.mero_backend.service.CtaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
