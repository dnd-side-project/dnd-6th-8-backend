package com.travel.domain.user.repository;

import com.travel.domain.archive.entity.Archives;
import com.travel.domain.user.entity.Survey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SurveyRepository  extends JpaRepository<Survey, Long> {
}
