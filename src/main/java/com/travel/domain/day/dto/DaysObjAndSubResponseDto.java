package com.travel.domain.day.dto;

import com.travel.domain.day.entity.Days;
import com.travel.domain.day.entity.DayImage;
import com.travel.domain.day.entity.DaysInfo;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class DaysObjAndSubResponseDto {
    private int dayNumber;
    private ArrayList<String> imgUrl;
    private DaysObjectiveResponseDto daysObjectiveResponseDto;
    private DaysSubjectiveResponseDto daysSubjectiveResponseDto;

    public DaysObjAndSubResponseDto(int dayNum, ArrayList<String> imgUrl, DaysObjectiveResponseDto daysObjectiveResponseDto, DaysSubjectiveResponseDto daysSubjectiveResponseDto) { //DaysInArchiveDto daysInArchiveDto
        this.dayNumber = dayNum;
        this.imgUrl = imgUrl;
        this.daysObjectiveResponseDto = daysObjectiveResponseDto;
        this.daysSubjectiveResponseDto = daysSubjectiveResponseDto;
    }

    public static List<DaysObjAndSubResponseDto> listOf(List<Days> filteredDays, List<DaysInfo> filteredDaysInfos, List<DayImage> filteredDaysImgs) {
        List<DaysObjAndSubResponseDto> dayResponses = new ArrayList<>();
        for (int i = 1; i<=filteredDays.size(); i++) {
            int dayNum = i;
            Days day = filteredDays.stream().filter(d->d.getDayNumber()==dayNum).findAny().get();
            ArrayList<String> imgUrl = new ArrayList<>();
            List<DayImage> dayImgs = filteredDaysImgs.stream().filter(img->img.getDays().getId()==day.getId()).collect(Collectors.toList());

            for (int j = 0; j<dayImgs.size(); j++) {
                imgUrl.add(dayImgs.get(j).getImageUrl());
            }

            DaysSubjectiveResponseDto daysSubjectiveResponseDto = new DaysSubjectiveResponseDto(day);

            DaysInfo daysInfo = filteredDaysInfos.stream().filter(d->d.getDays().getDayNumber()==dayNum).findAny().get();
            DaysObjectiveResponseDto daysObjectiveResponseDto = new DaysObjectiveResponseDto(daysInfo);

            dayResponses.add(new DaysObjAndSubResponseDto(dayNum, imgUrl, daysObjectiveResponseDto, daysSubjectiveResponseDto));
        }

        return dayResponses;
    }
}
