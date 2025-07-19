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
        // frameManagement는 FROM 절에서 시작되거나 INNER JOIN 되어 null이 아닐 가능성이 높지만, 안전하게 null 체크.
        this.frameMngId = (frameManagement != null) ? frameManagement.getFrameMngId() : null;
        this.frameId = (frameManagement != null) ? frameManagement.getFrameId() : null;
        this.companyId = (frameManagement != null) ? frameManagement.getCompanyId() : null;

        // designManagement는 FROM 절에서 시작되므로 null이 아닐 것입니다.
        this.designId = designManagement.getDesignId();

        // ShootSettings는 LEFT JOIN이므로 null일 수 있습니다.
        this.countdownTime = (shootSettings != null) ? shootSettings.getCountdownTime() : null;
        this.shotCount = (shootSettings != null) ? shootSettings.getShotCount() : null;
        this.printCount = (shootSettings != null) ? shootSettings.getPrintCount() : null;

        // Frame은 LEFT JOIN FETCH fm.frame f 이므로 fm이 null이 아니면 f도 보통 null이 아닙니다.
        // 하지만 혹시 모를 경우를 대비하여 null 체크.
        this.frameWidth = (frame != null) ? frame.getFrameWidth() : null;
        this.frameHeight = (frame != null) ? frame.getFrameHeight() : null;
    }
}