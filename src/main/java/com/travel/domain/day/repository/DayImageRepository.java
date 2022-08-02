package com.travel.domain.day.repository;

import com.travel.domain.day.entity.DayImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DayImageRepository extends JpaRepository<DayImage, Long> {
    @Query("select i from DayImage as i where i.archives.id=:archiveId")
    List<DayImage> findByArchiveId(@Param(value = "archiveId") Long archiveId);
}
