package com.mero.mero_backend.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "COMPANY_SALT")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompanySalt {
    @Id
    @Column(name = "COMPANY_ID")
    private String companyId;

    @OneToOne
    @JoinColumn(name = "COMPANY_ID")
    private CompanyInfo companyInfo;

    @Column(name = "SALT")
    private String salt;
}