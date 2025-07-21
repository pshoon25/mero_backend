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
    private Integer countdownTime;
    private Integer shotCount;
    private Integer printCount;
    private Integer frameWidth;
    private Integer frameHeight;
    private Integer frameCellCnt;
    private Integer cellWidth;
    private Integer cellHeight;
    private Integer borderTop;
    private Integer borderBottom;
    private Integer borderLeft;
    private Integer borderRight;
    private Integer crossbarHorizontal;
    private Integer crossbarVertical;

    public FrameDesignAndSettingsResponseDto(FrameManagement frameManagement, DesignManagement designManagement, ShootSettings shootSettings, Frame frame) {
        this.frameMngId = (frameManagement != null) ? frameManagement.getFrameMngId() : null;
        this.frameId = (frameManagement != null) ? frameManagement.getFrameId() : null;
        this.companyId = (frameManagement != null) ? frameManagement.getCompanyId() : null;
        this.designId = designManagement.getDesignId();
        this.countdownTime = (shootSettings != null) ? shootSettings.getCountdownTime() : null;
        this.shotCount = (shootSettings != null) ? shootSettings.getShotCount() : null;
        this.printCount = (shootSettings != null) ? shootSettings.getPrintCount() : null;
        this.frameWidth = (frame != null) ? frame.getFrameWidth() : null;
        this.frameHeight = (frame != null) ? frame.getFrameHeight() : null;
        this.frameCellCnt = (frame != null) ? frame.getFrameCellCnt() : null;
        this.cellWidth = (frame != null) ? frame.getCellWidth() : null;
        this.cellHeight = (frame != null) ? frame.getCellHeight() : null;
        this.borderTop = (frame != null) ? frame.getBorderTop() : null;
        this.borderBottom = (frame != null) ? frame.getBorderBottom() : null;
        this.borderLeft = (frame != null) ? frame.getBorderLeft() : null;
        this.borderRight = (frame != null) ? frame.getBorderRight() : null;
        this.crossbarHorizontal = (frame != null) ? frame.getCrossbarHorizontal() : null;
        this.crossbarVertical = (frame != null) ? frame.getCrossbarVertical() : null;
    }
}
