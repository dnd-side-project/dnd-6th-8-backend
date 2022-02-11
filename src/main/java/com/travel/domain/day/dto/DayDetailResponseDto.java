package com.travel.domain.day.dto;

import com.travel.domain.day.entity.Days;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@ApiModel(value = "데이 피드 읽기(Detail)")
public class DayDetailResponseDto {

    @ApiModelProperty(value = "데이 넘버(n일차)", example = "1")
    private int dayNumber;

    @ApiModelProperty(value = "여행 날짜", example="yyyy-MM-dd")
    private LocalDate date;

    @ApiModelProperty(value = "날씨", example="맑음")
    private String weather;

    @ApiModelProperty(value = "날씨", example="맑음")
    private String travelDescription;

    private String emotionDescription;

    public DayDetailResponseDto(Days entity){
        this.dayNumber = entity.getDayNumber();
        this.date = entity.getDate();
        this.weather = entity.getWeather();
        this.travelDescription = entity.getTravelDescription();
        this.emotionDescription = entity.getEmotionDescription();
    }
}