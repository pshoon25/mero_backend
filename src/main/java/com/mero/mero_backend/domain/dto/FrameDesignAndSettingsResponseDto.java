package com.mero.mero_backend.domain.dto;

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
}