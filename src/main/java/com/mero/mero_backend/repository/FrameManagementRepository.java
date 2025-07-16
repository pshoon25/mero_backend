package com.mero.mero_backend.repository;

import com.mero.mero_backend.domain.dto.FrameDesignResponseDto;
import com.mero.mero_backend.domain.entity.FrameManagement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FrameManagementRepository extends JpaRepository<FrameManagement, String> {
    @Query("SELECT COALESCE(CAST(MAX(CAST(SUBSTRING(fm.frameMngId, 9, 2) AS INTEGER)) AS INTEGER), 0) FROM FrameManagement fm WHERE SUBSTRING(fm.frameMngId, 1, 8) = :date")
    Integer findMaxIdByDate(@Param("date") String date); 

    Optional<FrameManagement> findByFrameIdAndCompanyId(String frameId, String companyId); 
}
