package com.mero.mero_backend.repository;

import com.mero.mero_backend.domain.dto.FrameDesignAndSettingsResponseDto;
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

//    @Query("SELECT NEW com.mero.mero_backend.domain.dto.FrameDesignAndSettingsResponseDto(" + // DTO의 패키지 경로와 클래스 이름 명시
//            "  fm.frameMngId, " +
//            "  fm.frameId, " + // FrameManagement 엔티티에 frameId가 직접 있다면 사용
//            "  fm.companyId, " + // FrameManagement 엔티티에 companyId가 직접 있다면 사용
//            "  dm.designId, " + // DESIGN_MANAGEMENT 엔티티의 designId
//            "  ss.countdownTime, " +
//            "  ss.shotCount, " +
//            "  ss.printCount, " +
//            "  f.frameWidth, " +
//            "  f.frameHeight" +
//            ") " +
//            "FROM FrameManagement fm " +
//            "LEFT JOIN DesignManagement dm ON (dm.companyId = fm.companyId AND dm.frameMngId = fm.frameMngId) " + // dm.frameMngId는 필드명에 맞게
//            "INNER JOIN ShootSettings ss ON (ss.companyId = fm.companyId AND ss.frameId = fm.frameId) " + // ss.companyId, ss.frameId는 필드명에 맞게
//            "INNER JOIN Frame f ON f.frameId = fm.frameId " + // f.frameId는 필드명에 맞게
//            "WHERE fm.companyId = :companyId AND fm.frameId = :frameId") // 특정 프레임과 회사에 대한 조회 조건 추가
//    List<FrameDesignAndSettingsResponseDto> getFrameDesignAndSettingsByCompanyIdAndFrameId(
//            @Param("companyId") String companyId,
//            @Param("frameId") String frameId
//    );

    @Query("SELECT new com.mero.mero_backend.domain.dto.FrameDesignAndSettingsResponseDto(fm, dm, ss, f) " +
            "FROM DesignManagement dm " +
            "LEFT JOIN FrameManagement fm ON fm.frameMngId = dm.frameMngId " +
            "LEFT JOIN FETCH fm.companyInfo ci " +
            "LEFT JOIN FETCH fm.frame f " +
            "LEFT JOIN FETCH f.shootSettings ss " +
            "WHERE f.frameId = :frameId AND ci.companyId = :companyId")
    List<FrameDesignAndSettingsResponseDto> getFrameDesignAndSettingsByCompanyIdAndFrameId(
            @Param("frameId") String frameId,
            @Param("companyId") String companyId
    );
}
