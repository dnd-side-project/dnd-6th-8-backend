package com.travel.domain.day.dto;

import com.travel.domain.day.entity.Days;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class DaysResponseDto {

    private Long id;
    private String dayNumber;
    private LocalDate date;
    private String weather;
    private String travelDescription;
    private String emotionDescription;

    public DaysResponseDto(Days entity){
        this.id = entity.getId();
        this.dayNumber = entity.getDayNumber();
        this.date = entity.getDate();
        this.weather = entity.getWeather();
        this.travelDescription = entity.getTravelDescription();
        this.emotionDescription = entity.getEmotionDescription();
    }
}