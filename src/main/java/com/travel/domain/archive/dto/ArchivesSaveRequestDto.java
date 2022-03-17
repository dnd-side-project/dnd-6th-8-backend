package com.travel.domain.archive.dto;

import com.travel.domain.archive.entity.*;
import com.travel.domain.user.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;


@Getter
@ApiModel(value = "아카이브 기록하기")
public class ArchivesSaveRequestDto {

    @ApiModelProperty(value = "아카이브 제목", example = "대충 다녀도 아름다운 제주", required = true)
    private String title;

    @ApiModelProperty(value = "여행 장소", example = "부산", required = true)
    private String place;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "출발 날짜", example = "2021-12-10", required = true)
    private LocalDate firstDay;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "마지막 날짜", example = "2021-12-15", required = true)
    private LocalDate lastDay;

    @ApiModelProperty(value = "동행 여부", example = "함께", required = true)
    private boolean haveCompanion;

    @ApiModelProperty(value = "예산 계획", example = "최소한", required = true)
    private EBudget budget;

    @ApiModelProperty(value = "기록 스타일", example = "감성", required = true)
    private EArchivingStyle archivingStyle;


    @Builder
    public ArchivesSaveRequestDto(String title, String place , LocalDate firstDay, LocalDate lastDay,
                                  boolean haveCompanion, EBudget budget, EArchivingStyle archivingStyle) {
        this.title = title;
        this.place = place;
        this.firstDay = firstDay;
        this.lastDay = lastDay;
        this.haveCompanion = haveCompanion;
        this.budget = budget;
        this.archivingStyle = archivingStyle;
    }

    public Archives toEntity(User user, Place place) {
        return Archives.builder().title(title).place(place).firstDay(firstDay).lastDay(lastDay)
                .haveCompanion(haveCompanion).budget(budget).archivingStyle(archivingStyle).user(user).build();
    }
}
