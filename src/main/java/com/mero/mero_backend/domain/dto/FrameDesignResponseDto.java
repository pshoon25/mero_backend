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
    private String companyId;
    private String useYn;
    private String designId;

    public FrameDesignResponseDto(FrameManagement frameManagement, String designId) {
        this.frameMngId = frameManagement.getFrameMngId();
        this.frameId = frameManagement.getFrame().getFrameId();
        this.companyId = frameManagement.getCompanyInfo().getCompanyId();
        this.useYn = frameManagement.getUseYn();
        this.designId = designId;
    }
}
