package com.mero.mero_backend.domain.dto;

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
    private Integer countdownTime; // shoot_settings
    private Integer shotCount;     // shoot_settings
    private Integer printCount;    // shoot_settings
    private Integer frameWidth;    // frame
    private Integer frameHeight;   // frame

    public FrameDesignAndSettingsResponseDto(
            String frameMngId,
            String frameId,
            String companyId,
            String designId,
            Integer countdownTime,
            Integer shotCount,
            Integer printCount,
            Integer frameWidth,
            Integer frameHeight
    ) {
        this.frameMngId = frameMngId;
        this.frameId = frameId;
        this.companyId = companyId;
        this.designId = designId;
        this.countdownTime = countdownTime;
        this.shotCount = shotCount;
        this.printCount = printCount;
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
    }
}