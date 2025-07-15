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
    private String useYn; 
    private String designId;
    private String designImageUrl;

    public FrameResponseDto(Frame frame, String frameMngUseYn, String designId, String designImageUrl) {
        this.frameId = frame.getFrameId();
        this.frameName = frame.getFrameName();
        this.useYn = frameMngUseYn;
        this.designId = designId;       
        this.designImageUrl = designImageUrl; 
    }

    public FrameResponseDto(Frame frame) {
        this.frameId = frame.getFrameId();
        this.frameName = frame.getFrameName();
        this.useYn = frame.getUseYn(); // Or assign a default like "UNKNOWN" or null

        if (frame.getDesignManagement() != null) {
            this.designId = frame.getDesignManagement().getDesignId();
            this.designImageUrl = frame.getDesignManagement().getDesignImageUrl();
        }
    }
}
