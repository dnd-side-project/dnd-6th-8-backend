package com.travel.domain.day.dto;

import com.travel.domain.day.entity.Days;

import java.time.LocalDate;

public class DaysResponseDto {

    private Long id;
    private Integer dayNumber;
    private LocalDate date;
    private String weather;
    private String image;
    private String travelDescription;
    private String emotionDescription;
    private String tipDescription;

    public DaysResponseDto(Days entity){
        this.id = entity.getId();
//        this.dayNumber = entity.getDayNumber();
//        this.date = entity.getDate();
//        this.weather = entity.getWeather();
//        this.image = entity.getImage();
        this.travelDescription = entity.getTravelDescription();
        this.emotionDescription = entity.getEmotionDescription();
        this.tipDescription = entity.getTipDescription();
    }
}