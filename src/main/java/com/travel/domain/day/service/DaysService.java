package com.travel.domain.day.service;

import com.travel.domain.day.dto.DayDetailResponseDto;
import com.travel.domain.day.dto.DaysSaveRequestDto;
import com.travel.domain.day.entity.Days;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface DaysService {

    public DayDetailResponseDto saveDay(DaysSaveRequestDto daysSaveRequestDto);

    public List<DayDetailResponseDto> findByArchiveIdAndDayNumber(Days archiveId, Days dayNumber);

    public void delete(Long id);

    public void updateDay(Long id, DaysSaveRequestDto daysSaveRequestDto);

}
