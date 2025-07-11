package com.mero.mero_backend.domain.dto;

import com.mero.mero_backend.domain.entity.CompanyInfo;
import com.mero.mero_backend.domain.entity.Frame;
import com.mero.mero_backend.domain.entity.DesignManagement;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class FrameRequestDto {
    private String frameId;
    private String companyId;
    private String applicationType;
    private String frameName;
    private String useYn;

    public FrameRequestDto(CompanyInfo companyInfo, DesignManagement designManagement, Frame frame) {
        this.frameId = frame.getFrameId();
        this.companyId = companyInfo.getCompanyId();
        this.applicationType = designManagement.getApplicationType();
        this.frameName = frame.getFrameName();
        this.useYn = frame.getUseYn();
    }
}
