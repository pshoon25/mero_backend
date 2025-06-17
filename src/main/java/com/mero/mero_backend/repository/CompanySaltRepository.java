package com.mero.mero_backend.repository;

import com.mero.mero_backend.domain.entity.CompanySalt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanySaltRepository extends JpaRepository<CompanySalt, Long> {
    CompanySalt findByCompanyId(String companyId);
}