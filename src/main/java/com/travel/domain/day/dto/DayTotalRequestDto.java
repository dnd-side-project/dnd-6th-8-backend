package com.travel.domain.day.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Data
public class DayTotalRequestDto {
    private ArrayList<DaysSaveRequestDto> daysSaveRequestDto;
    private String testing;
}
