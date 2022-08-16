package com.travel.domain.day.dto;

import com.travel.domain.day.entity.DaysInfo;
import com.travel.domain.day.entity.ETransportation;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class DaysObjectiveResponseDto {
    private Long daysInfo_Id;
    private String departure;
    private String arrival;
    private String travelTime;
    private ETransportation transportation;

    public DaysObjectiveResponseDto(DaysInfo entity){
        this.daysInfo_Id = entity.getId();
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
