package com.mero.mero_backend.repository;

import com.mero.mero_backend.domain.entity.InquiryDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CtaRepository extends JpaRepository<InquiryDetails, String> {
    @Query("SELECT COALESCE(CAST(MAX(CAST(SUBSTRING(i.inquiryId, 9, 2) AS INTEGER)) AS INTEGER), 0) FROM InquiryDetails i WHERE SUBSTRING(i.inquiryId, 1, 8) = :date")
    Integer findMaxIdByDate(@Param("date") String date);
}
