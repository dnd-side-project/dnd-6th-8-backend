package com.travel.domain.day.service;

import com.travel.domain.day.repository.DaysRepository;
import com.travel.domain.day.dto.DaysResponseDto;
import com.travel.domain.day.dto.DaysSaveRequestDto;
import com.travel.domain.day.entity.Days;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class DayServiceImpl implements DaysService {
    private final DaysRepository daysRepository;

    @Override
    @Transactional
    public Long save(DaysSaveRequestDto daysSaveRequestDto) {
        return daysRepository.save(daysSaveRequestDto.toEntity()).getId();
    }

    @Override
    @Transactional
    public DaysResponseDto findById(Long id) {
        Days day = daysRepository.findById(id).orElseThrow
                (() -> new IllegalArgumentException("해당 피드가 없습니다. id = " + id));
        return new DaysResponseDto(day);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Days day = daysRepository.findById(id).orElseThrow
                (() -> new IllegalArgumentException("해당 피드가 없습니다. id = " + id));
        daysRepository.delete(day);
    }
}
