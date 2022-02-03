package com.travel.domain.user.dto;

import com.travel.domain.archive.entity.Archives;
import com.travel.domain.archive.entity.EArchivingStyle;
import com.travel.domain.archive.entity.EBudget;
import com.travel.domain.archive.entity.EPlaces;
import com.travel.domain.user.entity.Survey;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@ApiModel(value = "서베이 작성 모델")
public class SurveySaveRequestDto {

    @ApiModelProperty(value = "아카이빙 스타일", example = "감성", required = true)
    EArchivingStyle archivingStyle;

    @ApiModelProperty(value = "예산", example = "최소한", required = true)
    EBudget budget;

    @ApiModelProperty(value = "동행여부", example = "true", required = true)
    private boolean haveCompanion;

    @Builder
    public SurveySaveRequestDto(EArchivingStyle archivingStyle, EBudget budget, boolean haveCompanion) {
        this.haveCompanion = haveCompanion;
        this.budget = budget;
        this.archivingStyle = archivingStyle;
    }

    public Survey toEntity() {
        return Survey.builder().archivingStyle(archivingStyle).budget(budget)
                .haveCompanion(haveCompanion).build();
    }


}
