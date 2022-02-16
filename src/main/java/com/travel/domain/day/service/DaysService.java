package com.travel.domain.day.service;

import com.travel.domain.day.dto.DayDetailResponseDto;
import com.travel.domain.day.dto.DaysSaveRequestDto;
import org.springframework.transaction.annotation.Transactional;

public interface DaysService {

    public DayDetailResponseDto saveDay(DaysSaveRequestDto daysSaveRequestDto);

    public DayDetailResponseDto getDay(Long archiveId, int dayNumber);

    public void delete(Long id);

    public void updateDay(Long id, DaysSaveRequestDto daysSaveRequestDto);

}
