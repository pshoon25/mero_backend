package com.mero.mero_backend.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "DESIGN_MANAGEMENT")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DesignManagement {
    @Id
    @Column(name = "DESIGN_ID")
    private String designId;

    @Column(name = "COMPANY_ID", nullable = false)
    private String companyId;

    @Column(name = "APPLICATION_TYPE", nullable = false)
    private String applicationType;

    @Column(name = "DESIGN_IMAGE_URL", nullable = false)
    private String designImageUrl;

    @Column(name = "FILE_NAME")
    private String fileName;

    @Column(name = "FILE_SIZE")
    private Long fileSize;

    @Column(name = "IMAGE_WIDTH")
    private Integer imageWidth;

    @Column(name = "IMAGE_HEIGHT")
    private Integer imageHeight;

    @Column(name = "UPLOAD_DATE")
    private LocalDateTime uploadDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COMPANY_ID", referencedColumnName = "COMPANY_ID", insertable = false, updatable = false)
    private CompanyInfo companyInfo;
}
