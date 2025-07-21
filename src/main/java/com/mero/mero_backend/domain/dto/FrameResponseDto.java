package com.mero.mero_backend.domain.dto;

import com.mero.mero_backend.domain.entity.Frame;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter 
@Setter
@NoArgsConstructor
public class FrameResponseDto {
    private String frameId;
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
    private String designId;
    private String designImageUrl;

    public FrameResponseDto(Frame frame, String frameMngUseYn, String designId, String designImageUrl) {
        this.frameId = frame.getFrameId();
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
        this.useYn = frameMngUseYn;
        this.designId = designId;       
        this.designImageUrl = designImageUrl; 
    }

    public FrameResponseDto(Frame frame) {
        this.frameId = frame.getFrameId();
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

        if (frame.getDesignManagement() != null) {
            this.designId = frame.getDesignManagement().getDesignId();
            this.designImageUrl = frame.getDesignManagement().getDesignImageUrl();
        }
    }
}
