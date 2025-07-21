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
    private String useYn;

    public FrameRequestDto(CompanyInfo companyInfo, DesignManagement designManagement, Frame frame) {
        this.frameId = frame.getFrameId();
        this.companyId = companyInfo.getCompanyId();
        this.applicationType = designManagement.getApplicationType();
        this.frameName = frame.getFrameName();
        this.frameWidth = frame.getFrameWidth();
        this.frameHeight = frame.getFrameHeight();
        this.frameCellCnt = frame.getFrameCellCnt();
        this.cellWidth = frame.getCellWidth();
        this.cellHeight = frame.getCellHeight();
        this.borderTop = frame.getBorderTop();
        this.borderBottom = frame.getBorderBottom();
        this.borderLeft = frame.getBorderLeft();
        this.borderRight = frame.getBorderRight();
        this.crossbarHorizontal = frame.getCrossbarHorizontal();
        this.crossbarVertical = frame.getCrossbarVertical();
        this.useYn = frame.getUseYn();
    }
}
