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

    @ApiModelProperty(value = "아카이브 제목", example = "1")
    private String title;

    @ApiModelProperty(value = "아카이브 id", example = "1")
    private LocalDate firstDay;

    @ApiModelProperty(value = "아카이브 id", example = "1")
    private LocalDate lastDay;

    @ApiModelProperty(value = "아카이브 id", example = "1")
    private EPlaces places;
    private EArchivingStyle archivingStyle;
    private EBudget budget;
    private boolean haveCompanion;
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
