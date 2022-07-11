package com.travel.domain.day.repository;

import com.travel.domain.day.entity.DaysInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DaysInfoRepository extends JpaRepository<DaysInfo, Long> {
    @Query("select i from DaysInfo as i where i.days.archives.id=:archiveId")
    List<DaysInfo> findByArchiveId(@Param(value = "archiveId") Long archiveId);
}
