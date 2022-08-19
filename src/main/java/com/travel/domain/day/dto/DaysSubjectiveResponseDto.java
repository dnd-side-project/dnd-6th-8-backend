package com.travel.domain.day.dto;

import com.travel.domain.day.entity.Days;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@ApiModel(value = "데이 피드 읽기(Detail)")
public class DaysSubjectiveResponseDto {

    @ApiModelProperty(value = "여행 날짜", example="yyyy-MM-dd")
    private LocalDate date;

    @ApiModelProperty(value = "날씨", example="맑음")
    private String weather;

    @ApiModelProperty(value = "날씨", example="맑음")
    private String travelDescription;

    @ApiModelProperty(value = "감정 스티커", example = "힐링")
    private String emotionDescription;

    @ApiModelProperty(value = "꿀팁")
    private String tipDescription;

    public DaysSubjectiveResponseDto(Days entity){
        this.date = entity.getDate();
        this.weather = String.valueOf(entity.getWeather());
        this.travelDescription = entity.getTravelDescription();
        this.emotionDescription = entity.getEmotionDescription();
        this.tipDescription = entity.getTipDescription();
    }

    public static List<DaysSubjectiveResponseDto> listOf(List<Days> filtered) {
        List<DaysSubjectiveResponseDto> dayResponses = new ArrayList<>();

        for (Days day : filtered) {
            dayResponses.add(new DaysSubjectiveResponseDto(day));
        }

        return dayResponses;
    }
}