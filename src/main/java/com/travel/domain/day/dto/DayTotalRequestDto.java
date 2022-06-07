package com.travel.domain.day.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DayTotalRequestDto {
    private List<DaysSaveRequestDto> daysSaveRequestDto;
}
