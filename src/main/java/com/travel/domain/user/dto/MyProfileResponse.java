package com.travel.domain.user.dto;

import com.travel.domain.user.entity.Survey;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MyProfileResponse {
    private String userName;
    private String userEmail;
    private SurveyResponse surveyResponse;

    @Builder
    public MyProfileResponse(String userName, String userEmail, Survey survey){
        this.userName = userName;
        this.userEmail = userEmail;
        if(survey != null){
            surveyResponse = new SurveyResponse(survey);
        }

    }
}
