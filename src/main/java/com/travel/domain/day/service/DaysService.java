package com.travel.domain.day.service;

import com.travel.domain.day.dto.DayDetailResponseDto;
import com.travel.domain.day.dto.DaysSaveRequestDto;

public interface DaysService {

    public DayDetailResponseDto saveDay(DaysSaveRequestDto daysSaveRequestDto);

    public DayDetailResponseDto findById(Long id);

    public void delete(Long id);

    public void updateDay(Long id, DaysSaveRequestDto daysSaveRequestDto);

}
