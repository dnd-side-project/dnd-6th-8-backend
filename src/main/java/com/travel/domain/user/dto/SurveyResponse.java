package com.travel.domain.user.dto;

import com.travel.domain.archive.entity.EArchivingStyle;
import com.travel.domain.archive.entity.EBudget;
import com.travel.domain.user.entity.Survey;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;


@Getter
public class SurveyResponse {
    @ApiModelProperty(value = "아카이빙 스타일", example = "감성", required = true)
    EArchivingStyle archivingStyle;

    @ApiModelProperty(value = "예산", example = "최소한", required = true)
    EBudget budget;

    @ApiModelProperty(value = "동행여부", example = "true", required = true)
    private boolean haveCompanion;

    @Builder
    SurveyResponse(Survey survey){
        this.archivingStyle = survey.getArchivingStyle();
        this.budget = survey.getBudget();
        this.haveCompanion = survey.isHaveCompanion();
    }
}
