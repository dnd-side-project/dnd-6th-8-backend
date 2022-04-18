package com.travel.domain.user.service;

import com.travel.domain.user.dto.SurveySaveRequestDto;
import com.travel.domain.user.entity.Survey;
import com.travel.domain.user.entity.User;
import com.travel.domain.user.repository.SurveyRepository;
import com.travel.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class SurveyServiceImpl implements SurveyService{

    private final SurveyRepository surveyRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public void save(SurveySaveRequestDto surveySaveRequestDto, String userEmail) {
        User user = userRepository.findByEmail(userEmail);
        Survey survey = surveyRepository.save(surveySaveRequestDto.toEntity());
        user.addSurvey(survey);
        userRepository.save(user);
    }

}