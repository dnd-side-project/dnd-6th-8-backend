package com.travel.domain.day.service;

import com.travel.domain.day.dto.DaySubjectiveResponseDto;
import com.travel.domain.day.dto.DaysSaveRequestDto;

import java.util.List;

public interface DaysService {

    public List<DaySubjectiveResponseDto> saveDay(List<DaysSaveRequestDto> daysSaveRequestDto, Long archiveId);

    public List<DaySubjectiveResponseDto> getDays(Long archiveId, Integer dayNumber);

    public void delete(Long id);

    public void updateDay(Long id, DaysSaveRequestDto daysSaveRequestDto);

}