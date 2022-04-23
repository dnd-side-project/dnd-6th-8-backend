package com.travel.domain.scrap.dto;

import com.travel.domain.archive.entity.Archives;
import com.travel.domain.scrap.entity.Scraps;
import com.travel.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
public class MyScrapDto {
    public int countScraps;

    public static MyScrapDto from(Archives archives) {
        if(archives == null) {
            return null;
        }
        return MyScrapDto.builder()
                .countScraps(archives.countScraps())
                .build();
    }

    public static List<MyScrapDto> listFrom(List<Archives> archivesList) {
        if(archivesList == null){
            return null;
        }
        return archivesList.stream()
                .map(MyScrapDto::from)
                .collect(Collectors.toList());
    }
}
