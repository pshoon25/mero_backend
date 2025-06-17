package com.mero.mero_backend.repository;

import com.mero.mero_backend.domain.entity.CompanyInfo;
import com.mero.mero_backend.domain.entity.InquiryDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CompanyRepository extends JpaRepository<CompanyInfo, Long> {
    @Query("SELECT COALESCE(MAX(SUBSTRING(c.companyId, 9, 2)), 0) FROM CompanyInfo c WHERE SUBSTRING(c.companyId, 1, 8) = :date")
    int findMaxIdByDate(@Param("date") String date);

    CompanyInfo findByLoginId(String loginId);

    CompanyInfo findByCompany(String company);
}