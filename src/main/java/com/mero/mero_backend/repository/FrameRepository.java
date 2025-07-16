package com.mero.mero_backend.repository;

import com.mero.mero_backend.domain.entity.Frame;
import com.mero.mero_backend.domain.dto.FrameResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FrameRepository extends JpaRepository<Frame, String> {
    @Query("SELECT COALESCE(CAST(MAX(CAST(SUBSTRING(f.frameId, 9, 2) AS INTEGER)) AS INTEGER), 0) FROM Frame f WHERE SUBSTRING(f.frameId, 1, 8) = :date")
    Integer findMaxIdByDate(@Param("date") String date);

    @Query("SELECT new com.mero.mero_backend.domain.dto.FrameResponseDto(f) FROM Frame f JOIN FETCH f.designManagement dm JOIN FETCH dm.companyInfo")
    List<FrameResponseDto> getAllFramesWithDesignInfo();

    @Query("SELECT new com.mero.mero_backend.domain.dto.FrameResponseDto(f, COALESCE(fm.useYn, 'N'), dm.designId, dm.designImageUrl) " +
           "FROM Frame f " +
           "JOIN FETCH f.designManagement dm " + 
           "JOIN FETCH dm.companyInfo ci " +     
           "LEFT JOIN FrameManagement fm ON fm.frame = f AND fm.companyId = :companyId " + 
           "WHERE f.useYn = 'Y'") 
    List<FrameResponseDto> getAllFramesWithDesignInfoByCompanyId(@Param("companyId") String companyId);

    @Query("SELECT new com.mero.mero_backend.domain.dto.FrameResponseDto(f, COALESCE(fm.useYn, 'N'), dm.designId, dm.designImageUrl) " +
            "FROM Frame f " +
            "JOIN FETCH f.designManagement dm " +
            "JOIN FETCH dm.companyInfo ci " +
            "LEFT JOIN FrameManagement fm ON fm.frame = f AND fm.companyId = :companyId " +
            "WHERE f.useYn = 'Y' " +
            "AND fm.useYn = 'Y'")
    List<FrameResponseDto> getAllFramesWithDesignInfoByCompanyIdAndUseYn(@Param("companyId") String companyId);
}
