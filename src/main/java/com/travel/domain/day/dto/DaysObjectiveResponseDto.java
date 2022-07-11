package com.travel.domain.day.dto;

import com.travel.domain.day.entity.DaysInfo;
import com.travel.domain.day.entity.ETransportation;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class DaysObjectiveResponseDto {
    private String departure;
    private String arrival;
    private String travelTime;
    private ETransportation transportation;

//DAY 작성자 추가 후 다시 진행하기 (Day 객관 정보 추가)
//    public static List<DaysInfoResponseDto> listOf(List<DaysInfo> filtered) {
//        List<DaysInfoResponseDto> dayInfoResponses = new ArrayList<>();
//
//        for (DaysInfo daysInfo : filtered) {
//            dayInfoResponses.add(new DaysInfoResponseDto(daysInfo));
//        }
//
//        return dayInfoResponses;
//    }
    public DaysObjectiveResponseDto(DaysInfo entity){
        this.departure = entity.getDeparture();
        this.arrival = entity.getArrival();
        this.travelTime = entity.getTravelTime();
        this.transportation = entity.getTransportation();
    }

    public static List<DaysObjectiveResponseDto> listOf(List<DaysInfo> filtered) {
        List<DaysObjectiveResponseDto> daysObjectiveResponses = new ArrayList<>();

        for (DaysInfo daysInfo : filtered) {
            daysObjectiveResponses.add(new DaysObjectiveResponseDto(daysInfo));
        }

        return daysObjectiveResponses;
    }
}
