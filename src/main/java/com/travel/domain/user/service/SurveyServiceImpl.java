package com.travel.domain.user.service;

import com.travel.domain.user.dto.SurveySaveRequestDto;
import com.travel.domain.user.repository.SurveyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class SurveyServiceImpl implements SurveyService{

    private final SurveyRepository surveyRepository;

    @Override
    @Transactional
    public Long save(SurveySaveRequestDto surveySaveRequestDto) {
        return surveyRepository.save(surveySaveRequestDto.toEntity()).getId();
    }



}
