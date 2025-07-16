package com.mero.mero_backend.repository;

import com.mero.mero_backend.domain.entity.CompanyInfo;
import com.mero.mero_backend.domain.entity.InquiryDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CompanyRepository extends JpaRepository<CompanyInfo, String> {
    @Query("SELECT COALESCE(CAST(MAX(CAST(SUBSTRING(c.companyId, 9, 2) AS INTEGER)) AS INTEGER), 0) FROM CompanyInfo c WHERE SUBSTRING(c.companyId, 1, 8) = :date")
    Integer findMaxIdByDate(@Param("date") String date);

    CompanyInfo findByLoginId(String loginId);

    CompanyInfo findByCompany(String company);

    CompanyInfo findByCompanyId(String companyId);
}
