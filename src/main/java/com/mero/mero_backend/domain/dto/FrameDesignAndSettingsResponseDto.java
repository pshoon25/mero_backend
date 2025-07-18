package com.mero.mero_backend.domain.dto;

import com.mero.mero_backend.domain.entity.DesignManagement;
import com.mero.mero_backend.domain.entity.Frame;
import com.mero.mero_backend.domain.entity.FrameManagement;
import com.mero.mero_backend.domain.entity.ShootSettings;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FrameDesignAndSettingsResponseDto {
    private String frameMngId;
    private String frameId;
    private String companyId;
    private String designId;
    private String countdownTime;
    private String shotCount;
    private String printCount;
    private Integer frameWidth;
    private Integer frameHeight;

    public FrameDesignAndSettingsResponseDto(FrameManagement frameManagement, DesignManagement designManagement, ShootSettings shootSettings, Frame frame) {
        this.frameMngId = frameManagement.getFrameMngId();
        this.frameId = frameManagement.getFrameId();
        this.companyId = frameManagement.getCompanyId();
        this.designId = designManagement.getDesignId();
        this.countdownTime = shootSettings.getCountdownTime();
        this.shotCount = shootSettings.getShotCount();
        this.printCount =shootSettings.getPrintCount();
        this.frameWidth = frame.getFrameWidth();
        this.frameHeight = frame.getFrameHeight();
    }
}