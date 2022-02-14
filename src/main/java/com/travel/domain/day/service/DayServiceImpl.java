package com.travel.domain.day.service;

import com.travel.domain.day.repository.DaysRepository;
import com.travel.domain.day.dto.DayDetailResponseDto;
import com.travel.domain.day.dto.DaysSaveRequestDto;
import com.travel.domain.day.entity.Days;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DayServiceImpl implements DaysService {

    private final DaysRepository daysRepository;

    @Override
    @Transactional(readOnly=true)
    public DayDetailResponseDto saveDay(DaysSaveRequestDto daysSaveRequestDto) {
        Days day = daysRepository.save(daysSaveRequestDto.toEntity());
        return new DayDetailResponseDto(day);
    }

    @Override
    @Transactional(readOnly=true)
    public DayDetailResponseDto findById(Long id) {
        Days day = daysRepository.findById(id).orElseThrow
                (() -> new IllegalArgumentException("해당 피드가 없습니다. id = " + id));
        return new DayDetailResponseDto(day);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateDay(Long id, DaysSaveRequestDto daysSaveRequestDto) {
        Days day = daysRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 피드가 없습니다. id = " + id));

        if (daysSaveRequestDto.getDayNumber() != 0) {
            day.setDayNumber(daysSaveRequestDto.getDayNumber());
        }
        if (daysSaveRequestDto.getDate() != null) {
            day.setDate(daysSaveRequestDto.getDate());
        }
        if (daysSaveRequestDto.getWeather() != null) {
            day.setWeather(daysSaveRequestDto.getWeather());
        }
        if (daysSaveRequestDto.getImage() != null) {
            day.setImage(daysSaveRequestDto.getImage());
        }
        if (daysSaveRequestDto.getTravelDescription() != null) {
            day.setTravelDescription(daysSaveRequestDto.getTravelDescription());
        }
        if (daysSaveRequestDto.getEmotionDescription() != null) {
            day.setEmotionDescription(daysSaveRequestDto.getEmotionDescription());
        }
        if (daysSaveRequestDto.getTipDescription() != null) {
            day.setTipDescription(daysSaveRequestDto.getTipDescription());
        }

        daysRepository.save(day);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Days day = daysRepository.findById(id).orElseThrow
                (() -> new IllegalArgumentException("해당 피드가 없습니다. id = " + id));
        daysRepository.delete(day);
    }
}
