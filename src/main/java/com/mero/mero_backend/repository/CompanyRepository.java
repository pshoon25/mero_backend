package com.mero.mero_backend.repository;

import com.mero.mero_backend.domain.entity.CompanyInfo;
import com.mero.mero_backend.domain.entity.InquiryDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface CompanyRepository extends JpaRepository<CompanyInfo, String> {
    @Query("SELECT COALESCE(CAST(MAX(CAST(SUBSTRING(c.companyId, 9, 2) AS INTEGER)) AS INTEGER), 0) FROM CompanyInfo c WHERE SUBSTRING(c.companyId, 1, 8) = :date")
    Integer findMaxIdByDate(@Param("date") String date);

    CompanyInfo findByLoginId(String loginId);

    CompanyInfo findByCompany(String company);

    CompanyInfo findByCompanyId(String companyId);

    @Modifying
    @Query("UPDATE CompanyInfo c SET c.useYn = :useYn WHERE c.companyId = :companyId")
    int updateUseYnByCompanyId(@Param("companyId") String companyId, @Param("useYn") String useYn);

    @Modifying
    @Query("UPDATE CompanyInfo c " +
            "SET c.loginPw = :loginPw, " +
                "c.company = :company, " +
                "c.ceoName = :ceoName, " +
                "c.businessNumber = :businessNumber, " +
                "c.address = :address, " +
                "c.manager = :manager, " +
                "c.email = :email, " +
                "c.contactNumber = :contactNumber, " +
                "c.remarks = :remarks, " +
                "c.updateDateTime = :updateDateTime " +
            "WHERE c.companyId = :companyId")
    int updateCompanyInfo(
            @Param("companyId") String companyId,
            @Param("loginPw") String loginPw,
            @Param("company") String company,
            @Param("ceoName") String ceoName,
            @Param("businessNumber") String businessNumber,
            @Param("address") String address,
            @Param("manager") String manager,
            @Param("email") String email,
            @Param("contactNumber") String contactNumber,
            @Param("remarks") String remarks,
            @Param("updateDateTime") LocalDateTime updateDateTime);
}
