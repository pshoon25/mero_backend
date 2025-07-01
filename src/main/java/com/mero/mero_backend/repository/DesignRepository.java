package com.mero.mero_backend.repository;

import com.mero.mero_backend.domain.entity.DesignManagement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DesignRepository extends JpaRepository<DesignManagement, Long> {
    Optional<DesignManagement> findByCompanyIdAndApplicationType(String companyId, String applicationType);
}
