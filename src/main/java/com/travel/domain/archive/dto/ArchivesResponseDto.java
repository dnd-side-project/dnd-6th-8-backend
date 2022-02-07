package com.travel.domain.archive.dto;

import com.travel.domain.archive.entity.Archives;
import com.travel.domain.archive.entity.EArchivingStyle;
import com.travel.domain.archive.entity.EBudget;
import com.travel.domain.archive.entity.EPlaces;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@ApiModel(value = "아카이브")
public class ArchivesResponseDto {

    @ApiModelProperty(value = "아카이브 id", example = "1")
    private Long id;

    @ApiModelProperty(value = "아카이브 제목", example = "대충 다녀도 아름다운 제주")
    private String title;

    @ApiModelProperty(value = "출발 날짜", example = "2021-12-10")
    private LocalDate firstDay;

    @ApiModelProperty(value = "마지막 날짜", example = "2021-12-15")
    private LocalDate lastDay;

    @ApiModelProperty(value = "여행 장소", example = "부산")
    private EPlaces places;

    @ApiModelProperty(value = "기록 스타일", example = "감성")
    private EArchivingStyle archivingStyle;

    @ApiModelProperty(value = "예산 계획", example = "최소한")
    private EBudget budget;

    @ApiModelProperty(value = "동행 여부", example = "함께")
    private boolean haveCompanion;

    @ApiModelProperty(value = "아카이브 공유", example = "true")
    private boolean isShare;

    public ArchivesResponseDto(Archives entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.firstDay = entity.getFirstDay();
        this.lastDay = entity.getLastDay();
        this.places = entity.getPlace();
        this.archivingStyle = entity.getArchivingStyle();
        this.budget = entity.getBudget();
        this.haveCompanion = entity.isHaveCompanion();
        this.isShare = entity.isShare();
    }
}
