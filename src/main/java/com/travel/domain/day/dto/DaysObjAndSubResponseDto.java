package com.travel.domain.day.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DaysObjAndSubResponseDto {
    private DaysInArchiveDto daysInArchiveDto;
    private List<DaysObjectiveResponseDto> daysObjectiveResponseDtoList;
    private List<DaysSubjectiveResponseDto> daysSubjectiveResponseDtoList;

    public DaysObjAndSubResponseDto(List<DaysObjectiveResponseDto> daysObjectiveResponseDtoList, List<DaysSubjectiveResponseDto> daysSubjectiveResponseDtoList, DaysInArchiveDto daysInArchiveDto) {
        this.daysInArchiveDto = daysInArchiveDto;
        this.daysObjectiveResponseDtoList = daysObjectiveResponseDtoList;
        this.daysSubjectiveResponseDtoList = daysSubjectiveResponseDtoList;
    }
}
