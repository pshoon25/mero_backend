package com.mero.mero_backend.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "MEDIA_RESULTS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MediaResults {
    @Id
    @Column(name = "RESULT_ID")
    private String resultId;

    @Column(name = "COMPANY_ID", nullable = false)
    private String companyId;

    @Column(name = "FRAME_MNG_ID", nullable = false)
    private String frameMngId;

    @Column(name = "DESIGN_ID")
    private String designId;

    @Column(name = "RECORD_GROUP")
    private String recordGroup;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "RECORD_DATE_TIME")
    private LocalDate recordDateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COMPANY_ID", referencedColumnName = "COMPANY_ID", insertable = false, updatable = false)
    private CompanyInfo companyInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FRAME_MNG_ID", referencedColumnName = "FRAME_MNG_ID", insertable = false, updatable = false)
    private FrameManagement frameManagement;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DESIGN_ID", referencedColumnName = "DESIGN_ID", insertable = false, updatable = false)
    private DesignManagement designManagement;
}
