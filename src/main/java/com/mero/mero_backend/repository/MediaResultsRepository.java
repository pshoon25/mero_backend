package com.mero.mero_backend.repository;

import com.mero.mero_backend.domain.entity.MediaResults;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MediaResultsRepository extends JpaRepository<MediaResults, String> {
    @Query(value = "SELECT COALESCE(MAX(CAST(SUBSTRING(mr.recordGroup, 10, 4) AS INTEGER)), 0) " +
                   "FROM MediaResults mr " +
                   "WHERE SUBSTRING(mr.recordGroup, 1, 8) = :date AND mr.companyId = :companyId")
    Integer getRecordGroup(@Param("date") String date, @Param("companyId") String companyId);

    @Query(value = "SELECT COALESCE(MAX(CAST(SUBSTRING(mr.resultId, 21, 4) AS INTEGER)), 0) " +
                    "FROM MediaResults mr " +
                    "WHERE SUBSTRING(mr.resultId, 1, 10) = :companyId AND SUBSTRING(mr.resultId, 12, 8) = :date")
    Integer findMaxSequenceIdByCompanyIdAndDate(@Param("companyId") String companyId, @Param("date") String date);
}
