package com.travel.domain.day.dto;

import com.travel.domain.day.entity.ETransportation;
import lombok.Getter;

@Getter
public class DayObjectiveResponseDto {
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
}
