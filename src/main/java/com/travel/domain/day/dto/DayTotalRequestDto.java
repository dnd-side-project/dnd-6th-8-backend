package com.travel.domain.day.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class DayTotalRequestDto {
    private List<DaysSaveRequestDto> daysSaveRequestDto;
}
