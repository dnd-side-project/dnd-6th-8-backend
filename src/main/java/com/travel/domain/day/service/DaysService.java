package com.travel.domain.day.service;

import com.travel.domain.day.dto.DaysObjAndSubResponseDto;
import com.travel.domain.day.dto.DaysSubjectiveResponseDto;
import com.travel.domain.day.dto.DaysSaveRequestDto;

import java.util.List;

public interface DaysService {

    public List<DaysSubjectiveResponseDto> saveDay(List<DaysSaveRequestDto> daysSaveRequestDto, Long archiveId);

    public DaysObjAndSubResponseDto getDays(Long archiveId, Integer dayNumber);

    public void delete(Long id);

    public void updateDay(Long id, DaysSaveRequestDto daysSaveRequestDto);

}