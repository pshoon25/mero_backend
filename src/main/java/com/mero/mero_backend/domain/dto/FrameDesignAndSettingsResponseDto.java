package com.mero.mero_backend.domain.dto;

import com.mero.mero_backend.domain.entity.DesignManagement;
import com.mero.mero_backend.domain.entity.Frame;
import com.mero.mero_backend.domain.entity.FrameManagement;
import com.mero.mero_backend.domain.entity.ShootSettings;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class FrameDesignAndSettingsResponseDto {
    private String frameMngId;
    private String frameId;
    private String companyId;
    private String designId; // DESIGN_MANAGEMENT에서 가져온 design_id
    private String countdownTime; // shoot_settings
    private String shotCount;     // shoot_settings
    private String printCount;    // shoot_settings
    private Integer frameWidth;    // frame
    private Integer frameHeight;   // frame

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