package com.travel.domain.day.dto;

import com.travel.domain.day.entity.Days;
import lombok.Getter;

@Getter
public class DaysResponseDto {

    private Long id;
    private String day;
    private String weather;
    private String travelDescription;
    private String emotionDescription;

    public DaysResponseDto(Days entity){
        this.id = entity.getId();
        this.day = entity.getDay();
        this.weather = entity.getWeather();
        this.travelDescription = entity.getTravelDescription();
        this.emotionDescription = entity.getEmotionDescription();
    }
}