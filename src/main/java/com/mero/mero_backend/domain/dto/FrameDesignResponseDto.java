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

    // FrameManagement 객체 하나를 인자로 받는 생성자
    public FrameDesignResponseDto(FrameManagement fm) {
        this.frameMngId = fm.getFrameMngId();
        this.useYn = fm.getUseYn();

        if (fm.getFrame() != null) {
            this.frameId = fm.getFrame().getFrameId();
            if (fm.getFrame().getDesignManagement() != null) {
                this.designId = fm.getFrame().getDesignManagement().getDesignId();
            }
        }

        if (fm.getCompanyInfo() != null) {
            this.companyId = fm.getCompanyInfo().getCompanyId();
        }
    }
}