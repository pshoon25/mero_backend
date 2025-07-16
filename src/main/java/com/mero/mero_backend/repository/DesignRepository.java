package com.mero.mero_backend.repository;

import com.mero.mero_backend.domain.entity.DesignManagement;
import com.mero.mero_backend.domain.dto.FrameDesignResponseDto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DesignRepository extends JpaRepository<DesignManagement, String> {
    @Query("SELECT COALESCE(CAST(MAX(CAST(SUBSTRING(d.designId, 9, 2) AS INTEGER)) AS INTEGER), 0) FROM DesignManagement d WHERE SUBSTRING(d.designId, 1, 8) = :date")
    Integer findMaxIdByDate(@Param("date") String date);

    Optional<DesignManagement> findByCompanyIdAndApplicationType(String companyId, String applicationType);

    Optional<DesignManagement> findByCompanyIdAndApplicationTypeAndFrameMngId(String companyId, String applicationType, String frameMngId);

    @Query("SELECT new com.mero.mero_backend.domain.dto.FrameDesignResponseDto(fm, dm.designId) " +
        "FROM DesignManagement dm " +
        "LEFT JOIN FrameManagement fm ON fm.frameMngId = dm.frameMngId " + 
        "LEFT JOIN FETCH fm.companyInfo ci " +    
        "LEFT JOIN FETCH fm.frame f " +           
        "WHERE f.frameId = :frameId AND ci.companyId = :companyId")
List<FrameDesignResponseDto> findFrameDesignByFrameIdAndCompanyId(
        @Param("frameId") String frameId,
        @Param("companyId") String companyId
);
}
