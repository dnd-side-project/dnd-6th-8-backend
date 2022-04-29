package com.travel.domain.scrap.dto;
import com.travel.domain.scrap.entity.Scraps;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@ApiModel(value = "스크랩 목록")
public class ScrapPreviewDto {
    @ApiModelProperty(value = "스크랩 id", example = "1")
    private Long id;

    @ApiModelProperty(value = "스크랩한 시간", example = "2022-02-19T22:24:00")
    private LocalDateTime createdAt;

    private CountMyScrapsDto countMyScraps;

    public ScrapPreviewDto(Scraps entity) {
        this.id = entity.getId();
        this.createdAt = entity.getCreatedAt();
        this.countMyScraps = CountMyScrapsDto.from(entity.getUser());
    }

    public static List<ScrapPreviewDto> getScrapListOfUser(List<Scraps> scrapsFilteredByUser) {
        List<ScrapPreviewDto> scrapResponses = new ArrayList<>();

        for (Scraps scrap : scrapsFilteredByUser) {
            scrapResponses.add(new ScrapPreviewDto(scrap));
        }

        return scrapResponses;
    }

}
