package com.travel.domain.day.service;

import com.travel.domain.archive.entity.Archives;
import com.travel.domain.day.dto.DayDetailResponseDto;
import com.travel.domain.day.dto.DaysSaveRequestDto;
import com.travel.domain.day.entity.Days;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface DaysService {

    public DayDetailResponseDto saveDay(DaysSaveRequestDto daysSaveRequestDto, Long archiveId);

    public List<DayDetailResponseDto> getDays(Archives archives, Integer dayNumber);

    public void delete(Long id);

    public void updateDay(Long id, DaysSaveRequestDto daysSaveRequestDto);

}
