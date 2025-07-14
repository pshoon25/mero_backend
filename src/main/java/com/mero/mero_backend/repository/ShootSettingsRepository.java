package com.mero.mero_backend.repository;

import com.mero.mero_backend.domain.entity.ShootSettings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ShootSettingsRepository extends JpaRepository<ShootSettings, Long> {

}
