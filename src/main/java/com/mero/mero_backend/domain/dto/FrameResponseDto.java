package com.mero.mero_backend.domain.dto;

import com.mero.mero_backend.domain.entity.CompanyInfo;
import com.mero.mero_backend.domain.entity.Frame;
import com.mero.mero_backend.domain.entity.FrameManagement;
import com.mero.mero_backend.domain.entity.DesignManagement;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class FrameResponseDto {
    private String frameId;
    private String frameName;
    private String useYn;
    private DesignManagementDto designManagement; 

    public FrameResponseDto(Frame frame, FrameManagement frameManagement) {
        this.frameId = frame.getFrameId();
        this.frameName = frame.getFrameName();
        this.useYn = frameManagement.getUseYn();

        if (frame.getDesignManagement() != null) {
            this.designManagement = new DesignManagementDto(frame.getDesignManagement());
        }
    }
}
