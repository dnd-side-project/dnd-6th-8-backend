package com.travel.domain.day.repository;

import com.travel.domain.day.entity.Days;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.Optional;

public interface DaysRepository extends JpaRepository<Days, Long> {
    Optional<Days> findByArchiveIdAndDayNumber(Long archiveId, int dayNumber);
}