package com.mero.mero_backend.repository;

import com.mero.mero_backend.domain.entity.MediaResults;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MediaResultsRepository extends JpaRepository<MediaResults, String> {

}
