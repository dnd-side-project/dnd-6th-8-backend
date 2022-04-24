package com.travel.domain.scrap.dto;

import com.travel.domain.archive.entity.Archives;
import lombok.Builder;

import java.util.List;
import java.util.stream.Collectors;

@Builder
public class CountScrapsOfArchiveDto {
    public int countScrapsOfArchive; //한 아카이브의 스크랩 수

    public static CountScrapsOfArchiveDto from(Archives archives) {
        if(archives == null) {
            return null;
        }
        return CountScrapsOfArchiveDto.builder()
                .countScrapsOfArchive(archives.countScrapsOfArchive())
                .build();
    }

    public static List<CountScrapsOfArchiveDto> listFrom(List<Archives> archivesList) {
        if(archivesList == null){
            return null;
        }
        return archivesList.stream()
                .map(CountScrapsOfArchiveDto::from)
                .collect(Collectors.toList());
    }

}
