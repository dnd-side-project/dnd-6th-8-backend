package com.travel.domain.day.service;

import com.travel.domain.day.dto.DaysResponseDto;
import com.travel.domain.day.dto.DaysSaveRequestDto;

public interface DaysService {
    Long save(DaysSaveRequestDto daysSaveRequestDto);

    DaysResponseDto findById(Long id);

    void delete(Long id);
}
