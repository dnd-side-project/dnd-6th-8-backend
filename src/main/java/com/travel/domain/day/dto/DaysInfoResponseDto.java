package com.travel.domain.day.dto;

import com.travel.domain.day.entity.Days;
import com.travel.domain.day.entity.DaysInfo;
import com.travel.domain.day.entity.ETransportation;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class DayInfoResponseDto {
    private String departure;
    private String arrival;
    private String travelTime;
    private ETransportation transportation;

    public static List<DayInfoResponseDto> listOf(List<DaysInfo> filtered) {
        List<DayInfoResponseDto> dayInfoResponses = new ArrayList<>();

        for (DaysInfo daysInfo : filtered) {
            dayInfoResponses.add(new DayInfoResponseDto(daysInfo));
        }

        return dayInfoResponses;
    }
}
