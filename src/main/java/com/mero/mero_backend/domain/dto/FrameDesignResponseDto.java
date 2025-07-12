package com.mero.mero_backend.domain.dto;

import com.mero.mero_backend.domain.entity.CompanyInfo;
import com.mero.mero_backend.domain.entity.Frame;
import com.mero.mero_backend.domain.entity.DesignManagement;

import com.mero.mero_backend.domain.entity.FrameManagement;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class FrameDesignResponseDto {
    private String frameMngId;
    private String frameId;
    private String useYn;
    private String designId;
    private String companyId;

    public FrameDesignResponseDto(Frame frame, FrameManagement frameManagement, DesignManagement designManagement, CompanyInfo companyInfo) {
        this.frameMngId = frameManagement.getFrameMngId();
        this.frameId = frame.getFrameId();
        this.useYn = frameManagement.getUseYn();
        this.designId = designManagement.getDesignId();
        this.companyId = companyInfo.getCompanyId();
    }
}