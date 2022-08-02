package com.travel.domain.day.dto;

import com.travel.domain.archive.entity.Archives;
import com.travel.domain.day.entity.Days;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
public class DaysInArchiveDto {

    private String writer;
    private Long archiveId;
    private String archiveTitle;
    private LocalDate firstDay;
    private LocalDate lastDay;

    public DaysInArchiveDto(Archives entity) {
        this.writer = entity.getUser().getEmail();
        this.archiveId = entity.getId();
        this.archiveTitle = entity.getTitle();
        this.firstDay = entity.getFirstDay();
        this.lastDay = entity.getLastDay();
    }
}
