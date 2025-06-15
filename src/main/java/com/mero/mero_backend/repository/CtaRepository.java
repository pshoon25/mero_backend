package com.mero.mero_backend.repository;

import com.mero.mero_backend.domain.entity.InquiryDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CtaRepository extends JpaRepository<InquiryDetails, Long> {
    @Query("SELECT COALESCE(MAX(i.inquiryId), 0) FROM InquiryDetails i")
    Long findMaxInquiryId();
}
