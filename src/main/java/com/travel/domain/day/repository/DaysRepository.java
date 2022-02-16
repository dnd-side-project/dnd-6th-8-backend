package com.travel.domain.day.repository;

import com.travel.domain.day.entity.Days;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DaysRepository extends JpaRepository<Days, Long> {
    List<Days> findByArchiveIdAndDayNumber(@Param("archiveId")Days archiveId, @Param("dayNumber")Days dayNumber);
}