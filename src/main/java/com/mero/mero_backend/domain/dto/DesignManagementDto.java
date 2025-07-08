package com.mero.mero_backend.domain.dto;

import com.mero.mero_backend.domain.entity.DesignManagement;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DesignManagementDto {
    private String designId;
    private String designImageUrl;
    private String companyName;

    public DesignManagementDto(DesignManagement entity) {
        this.designId = entity.getDesignId();
        this.designImageUrl = entity.getDesignImageUrl();
        this.companyName = entity.getCompanyInfo() != null ? entity.getCompanyInfo().getCompany() : null;
    }

}