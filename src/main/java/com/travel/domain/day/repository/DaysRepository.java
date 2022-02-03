package com.travel.domain.day.repository;

import com.travel.domain.day.entity.Days;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DaysRepository extends JpaRepository<Days, Long> {
}