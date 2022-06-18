package com.travel.domain.scrap.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class ScrapListDto {
    public List<ScrapPreviewDto> scrapPreviewDto;
    public int countMyScraps;

    public ScrapListDto(List<ScrapPreviewDto> scrapPreviewDto, int countMyScraps) {
        this.scrapPreviewDto = scrapPreviewDto;
        this.countMyScraps = countMyScraps;
    }
}
