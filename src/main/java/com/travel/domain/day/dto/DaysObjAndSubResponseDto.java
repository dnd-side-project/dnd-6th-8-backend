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
    private List<DaysObjectiveResponseDto> daysObjectiveResponses;
    private DaysSubjectiveResponseDto daysSubjectiveResponseDto;

    public DaysObjAndSubResponseDto(int dayNum, ArrayList<String> imgUrl, List<DaysObjectiveResponseDto> daysObjectiveResponses, DaysSubjectiveResponseDto daysSubjectiveResponseDto) {
        this.dayNumber = dayNum;
        this.imgUrl = imgUrl;
        this.daysObjectiveResponses = daysObjectiveResponses;
        this.daysSubjectiveResponseDto = daysSubjectiveResponseDto;
    }

    public static List<DaysObjAndSubResponseDto> listOf(List<Days> filteredDays, List<DaysInfo> filteredDaysInfos, List<DayImage> filteredDaysImgs) {
        List<DaysObjAndSubResponseDto> dayResponses = new ArrayList<>();
        for (int i = 1; i<=filteredDays.size(); i++) {

            int dayNum = i;
            Days day = filteredDays.stream().filter(d->d.getDayNumber()==dayNum).findAny().get();

            List<DaysInfo> dayInfos = filteredDaysInfos.stream().filter(info->info.getDays().getId()==day.getId()).collect(Collectors.toList());
            List<DaysObjectiveResponseDto> daysObjectiveResponses = DaysObjectiveResponseDto.listOf(dayInfos);

            ArrayList<String> imgUrl = new ArrayList<>();
            List<DayImage> dayImgs = filteredDaysImgs.stream().filter(img->img.getDays().getId()==day.getId()).collect(Collectors.toList());

            for (int j = 0; j<dayImgs.size(); j++) {
                imgUrl.add(dayImgs.get(j).getImageUrl());
            }

            DaysSubjectiveResponseDto daysSubjectiveResponseDto = new DaysSubjectiveResponseDto(day);

            dayResponses.add(new DaysObjAndSubResponseDto(dayNum, imgUrl, daysObjectiveResponses, daysSubjectiveResponseDto));
        }

        return dayResponses;
    }
}
