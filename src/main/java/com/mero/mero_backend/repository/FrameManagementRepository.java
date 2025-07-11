package com.mero.mero_backend.repository;

import com.mero.mero_backend.domain.dto.FrameDesignResponseDto;
import com.mero.mero_backend.domain.entity.FrameManagement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FrameManagementRepository extends JpaRepository<FrameManagement, String> {
    @Query("SELECT COALESCE(CAST(MAX(CAST(SUBSTRING(fm.frameMngId, 9, 2) AS INTEGER)) AS INTEGER), 0) FROM FrameManagement fm WHERE SUBSTRING(fm.frame.frameId, 1, 8) = :date")
    Integer findMaxIdByDate(@Param("date") String date);

    @Query("SELECT new com.mero.mero_backend.domain.dto.FrameDesignResponseDto(fm) " +
            "FROM FrameManagement fm " +
            "JOIN FETCH fm.companyInfo ci " +
            "JOIN FETCH fm.frame f " +
            "WHERE fm.frame.frameId = :frameId AND fm.companyInfo.companyId = :companyId")
    List<FrameDesignResponseDto> findByFrameIdAndCompanyId(
            @Param("frameId") String frameId,
            @Param("companyId") String companyId
    );
}