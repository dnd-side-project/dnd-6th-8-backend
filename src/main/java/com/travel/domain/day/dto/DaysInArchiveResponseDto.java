package com.travel.domain.day.dto;

import com.travel.domain.archive.entity.Archives;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
public class DaysInArchiveResponseDto {

    private String writer;
    private Long archiveId;
    private String archiveTitle;
    private LocalDate firstDay;
    private LocalDate lastDay;
    private List<DaysObjAndSubResponseDto> daysObjAndSubResponseDto;

    public DaysInArchiveResponseDto(Archives entity, List<DaysObjAndSubResponseDto> daysObjAndSubResponseDto) {
        this.writer = entity.getUser().getEmail();
        this.archiveId = entity.getId();
        this.archiveTitle = entity.getTitle();
        this.firstDay = entity.getFirstDay();
        this.lastDay = entity.getLastDay();
        this.daysObjAndSubResponseDto = daysObjAndSubResponseDto;
    }
}
