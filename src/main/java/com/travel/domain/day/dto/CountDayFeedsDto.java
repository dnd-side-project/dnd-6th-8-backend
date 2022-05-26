package com.travel.domain.day.dto;

import com.travel.domain.archive.entity.Archives;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CountDayFeedsDto {
    public int countDayFeeds;

    public static CountDayFeedsDto from(Archives archive) {
        if (archive == null) {
            return null;
        }
        return CountDayFeedsDto.builder()
                .countDayFeeds(archive.countDaysFeedsOfArchive())
                .build();
    }
}
