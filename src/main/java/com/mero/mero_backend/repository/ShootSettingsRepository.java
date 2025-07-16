package com.mero.mero_backend.repository;

import com.mero.mero_backend.domain.entity.ShootSettings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ShootSettingsRepository extends JpaRepository<ShootSettings, String> {
    @Query("SELECT COALESCE(CAST(MAX(CAST(SUBSTRING(s.settingId, 9, 2) AS INTEGER)) AS INTEGER), 0) FROM ShootSettings s WHERE SUBSTRING(s.settingId, 1, 8) = :date")
    Integer findMaxIdByDate(@Param("date") String date);

    ShootSettings findByCompanyIdAndFrameId(String companyId, String frameId);
}
