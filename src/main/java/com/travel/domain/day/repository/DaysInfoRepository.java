package com.travel.domain.day.repository;

import com.travel.domain.day.entity.DaysInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DaysInfoRepository extends JpaRepository<DaysInfo, Long> {
}
