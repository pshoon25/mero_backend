package com.mero.mero_backend.repository;

import com.mero.mero_backend.domain.dto.FrameDesignResponseDto;
import com.mero.mero_backend.domain.entity.FrameManagement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FrameManagementRepository extends JpaRepository<FrameManagement, String> {

    @Query("SELECT new com.mero.mero_backend.domain.dto.FrameResponseDto(fm) " +
            "FROM FrameManagement fm " +
            "JOIN FETCH fm.companyInfo ci " +
            "JOIN FETCH fm.frame f " +
            "JOIN FETCH f.designManagement dm " +
            "WHERE dm.designId = :designId AND fm.companyInfo.companyId = :companyId")
    List<FrameDesignResponseDto> findByDesignIdAndCompanyId(
            @Param("designId") String designId,
            @Param("companyId") String companyId
    );
}