package com.travel.domain.day.dto;

import com.travel.domain.day.entity.Days;
import com.travel.domain.day.entity.DaysInfo;
import com.travel.domain.day.entity.ETransportation;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class DayInfoSaveRequestDto {
    private String departure;
    private String arrival;
    private String travelTime;
    private ETransportation transportation;
}
