package com.mero.mero_backend.domain.dto;

import com.mero.mero_backend.domain.entity.MediaResults;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Getter 
@Setter
@NoArgsConstructor
public class MedaiResultsResponseDto {
    private String resultId;
    private String companyId;
    private String frameMngId;
    private String designId;
    private String recordGroup;
    private String type;
    private LocalDate recordDateTime;

    public MedaiResultsResponseDto(MediaResults mediaResults) {
        this.resultId = mediaResults.getResultId();
        this.companyId = mediaResults.getCompanyId();
        this.frameMngId = mediaResults.getFrameMngId();
        this.designId = mediaResults.getDesignId();
        this.recordGroup = mediaResults.getRecordGroup();
        this.type = mediaResults.getType();
        this.recordDateTime = mediaResults.getRecordDateTime();
    }
}
