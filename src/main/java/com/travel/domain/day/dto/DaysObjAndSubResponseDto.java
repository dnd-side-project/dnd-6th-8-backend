package com.travel.domain.day.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DaysObjAndSubResponseDto {
    private List<DaysObjectiveResponseDto> daysObjectiveResponseDtoList;
    private List<DaysSubjectiveResponseDto> daysSubjectiveResponseDtoList;
}
