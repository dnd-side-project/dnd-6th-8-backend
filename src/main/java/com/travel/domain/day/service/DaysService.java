package com.travel.domain.day.service;

import com.travel.domain.day.dto.DaysResponseDto;
import com.travel.domain.day.dto.DaysSaveRequestDto;
import com.travel.domain.day.entity.Days;
import com.travel.domain.day.repository.DaysRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class DaysService {
    private final DaysRepository daysRepository;

    @Transactional
    public Long save(DaysSaveRequestDto daysSaveRequestDto){
        return daysRepository.save(daysSaveRequestDto.toEntity()).getId();
    }

    @Transactional
    public DaysResponseDto findById(Long id ){
        Days entity = daysRepository.findById(id).orElseThrow
                (()->new IllegalArgumentException("해당 피드가 없습니다. id = " + id));
        return new DaysResponseDto(entity);
    }
}
