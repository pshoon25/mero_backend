package com.mero.mero_backend.domain.dto;

import com.mero.mero_backend.domain.entity.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MediaResultsRequestDto {
    private String companyId;
    private String frameMngId;
    private String recordGroup;
    private String type;
    private String applicationType;

    public MediaResultsRequestDto(CompanyInfo companyInfo, FrameManagement frameManagement, MediaResults mediaResults, DesignManagement designManagement) {
        this.companyId = companyInfo.getCompanyId();
        this.frameMngId = frameManagement.getFrameMngId();
        this.recordGroup = mediaResults.getRecordGroup();
        this.type = mediaResults.getType();
        this.applicationType = designManagement.getApplicationType();
    }
}
