package com.mero.mero_backend.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "COMPANY_INFO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyInfo {
    @Id
    @Column(name = "COMPANY_ID", nullable = false)
    private String companyId; // 고유 ID ex)2025061601

    @Column(name = "LOGIN_ID", nullable = false)
    private String loginId; // 로그인 ID

    @Column(name = "LOGIN_PW", nullable = false)
    private String loginPw; // 로그인 PW

    @Column(name = "COMPANY_TYPE", nullable = false)
    private String companyType; // 사용자 구분 ex)ADMIN or USER

    @Column(name = "COMPANY", nullable = false)
    private String company; // 업체명

    @Column(name = "CEO_NAME")
    private String ceoName; // 대표자명

    @Column(name = "BUSINESS_NUMBER")
    private String businessNumber; // 사업자 등록번호

    @Column(name = "ADDRESS")
    private String address; // 주소

    @Column(name = "MANAGER")
    private String manager; // 담당자명

    @Column(name = "EMAIL")
    private String email; // 이메일

    @Column(name = "CONTACT_NUMBER")
    private String contactNumber; // 연락처

    @Column(name = "REMARKS")
    private String remarks; // 비고

    @Column(name = "USE_YN", nullable = false)
    private String useYn; // 사용여부

    @Column(name = "REGISTER_DATE_TIME")
    private LocalDateTime registerDateTime; // 등록일자

    @Column(name = "UPDATE_DATE_TIME")
    private LocalDateTime updateDateTime; // 수정일자
}
