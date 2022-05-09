package com.travel.domain.day.dto;

import com.travel.domain.day.entity.Days;
import com.travel.domain.day.entity.DaysInfo;
import com.travel.domain.day.entity.ETransportation;
import lombok.Builder;
import lombok.Getter;

@Getter
public class DayInfoSaveRequestDto {
    private String departure;
    private String arrival;
    private String travelTime;
    private ETransportation transportation;
}
