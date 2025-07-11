package com.mero.mero_backend.repository;

import com.mero.mero_backend.domain.entity.Frame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FrameRepository extends JpaRepository<Frame, Long> {
    @Query("SELECT COALESCE(CAST(MAX(CAST(SUBSTRING(f.frameId, 9, 2) AS INTEGER)) AS INTEGER), 0) FROM Frame f WHERE SUBSTRING(f.frameId, 1, 8) = :date")
    Integer findMaxIdByDate(@Param("date") String date);

    @Query("SELECT f FROM Frame f JOIN FETCH f.designManagement")
    List<Frame> findAllWithDesignManagement();
}
