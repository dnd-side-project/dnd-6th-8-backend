package com.travel.domain.day.dto;

import com.travel.domain.day.entity.Days;
import com.travel.domain.day.entity.DaysInfo;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class DaysObjAndSubResponseDto {
//    private DaysInArchiveDto daysInArchiveDto; 일단 이 dto를 Day set 이라고 생각해보기
    private Long dayNumber;
    private String daysImg;
    private DaysObjectiveResponseDto daysObjectiveResponseDto;
    private DaysSubjectiveResponseDto daysSubjectiveResponseDto;

    public DaysObjAndSubResponseDto(DaysObjectiveResponseDto daysObjectiveResponseDto, DaysSubjectiveResponseDto daysSubjectiveResponseDto) { //DaysInArchiveDto daysInArchiveDto
//        this.daysInArchiveDto = daysInArchiveDto;
        this.daysObjectiveResponseDto = daysObjectiveResponseDto;
        this.daysSubjectiveResponseDto = daysSubjectiveResponseDto;
    }

    public static List<DaysObjAndSubResponseDto> listOf(List<Days> filteredDays, List<DaysInfo> filteredDaysInfos) {
        List<DaysObjAndSubResponseDto> dayResponses = new ArrayList<>();
        for (int i = 1; i<=filteredDays.size(); i++) {
            int dayNum = i;
            Days day = filteredDays.stream().filter(d->d.getDayNumber()==dayNum).findAny().get();
            DaysSubjectiveResponseDto daysSubjectiveResponseDto = new DaysSubjectiveResponseDto(day);

            DaysInfo daysInfo = filteredDaysInfos.stream().filter(d->d.getDays().getDayNumber()==dayNum).findAny().get();
            DaysObjectiveResponseDto daysObjectiveResponseDto = new DaysObjectiveResponseDto(daysInfo);

            dayResponses.add(new DaysObjAndSubResponseDto(daysObjectiveResponseDto, daysSubjectiveResponseDto));
        }

        return dayResponses;
    }
}
