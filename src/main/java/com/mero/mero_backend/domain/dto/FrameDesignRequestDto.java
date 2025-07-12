package com.mero.mero_backend.domain.dto;

import com.mero.mero_backend.domain.entity.CompanyInfo;
import com.mero.mero_backend.domain.entity.DesignManagement;
import com.mero.mero_backend.domain.entity.Frame;
import com.mero.mero_backend.domain.entity.FrameManagement;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FrameDesignRequestDto {
    private String frameMngId;
    private String frameId;
    private String companyId;
    private String applicationType;
    private String useYn;

    public FrameDesignRequestDto(FrameManagement frameManagement, CompanyInfo companyInfo, DesignManagement designManagement, Frame frame) {
        this.frameMngId = frameManagement.getFrameMngId();
        this.frameId = frame.getFrameId();
        this.companyId = companyInfo.getCompanyId();
        this.applicationType = designManagement.getApplicationType();
        this.useYn = frameManagement.getUseYn();
    }
}
