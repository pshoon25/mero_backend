package com.mero.mero_backend.domain.dto;

import com.mero.mero_backend.domain.entity.Frame;
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
    private String designId;
    private String designImageUrl;

    public FrameResponseDto(Frame frame, String frameMngUseYn, DesignManagementDto designManagement) {
        this.frameId = frame.getFrameId();
        this.frameName = frame.getFrameName();
        this.useYn = frameMngUseYn; 
        this.designId = designManagement.getDesignId();
        this.designImageUrl = designManagement.getDesignImageUrl();
    }
}
