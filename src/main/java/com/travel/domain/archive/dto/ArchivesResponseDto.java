package com.travel.domain.archive.dto;

import com.travel.domain.archive.entity.Archives;
import lombok.Getter;

@Getter
public class ArchivesResponseDto {

    private Long id;
    private String title;

    public ArchivesResponseDto(Archives entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
    }
}