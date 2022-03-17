package com.travel.domain.user.service;

import com.travel.domain.user.dto.SurveySaveRequestDto;

public interface SurveyService {
    public void save(SurveySaveRequestDto surveySaveRequestDto, String userEmail);
}
