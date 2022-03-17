package com.travel.domain.day.repository;

import com.travel.domain.archive.entity.Archives;
import com.travel.domain.day.entity.Days;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DaysRepository extends JpaRepository<Days, Long> {
    @Query("select d from Days as d where d.archives.id=:archiveId")
    List<Days> findByArchivesAndDayNumber(Long archiveId);
}