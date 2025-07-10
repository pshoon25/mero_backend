package com.mero.mero_backend.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "FRAME_MANAGEMENT")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FrameManagement {
    @Id
    @Column(name = "FRAME_MNG_ID")
    private String frameMngId;

    @Column(name = "COMPANY_ID", nullable = false)
    private String companyId;

    @Column(name = "FRAME_ID", nullable = false)
    private String frameId;

    @Column(name = "USE_YN", nullable = false, columnDefinition = "CHAR(1) DEFAULT 'Y'")
    private String useYn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COMPANY_ID", referencedColumnName = "COMPANY_ID", insertable = false, updatable = false)
    private CompanyInfo companyInfo;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FRAME_ID", referencedColumnName = "FRAME_ID", insertable = false, updatable = false)
    private Frame frame;
}
