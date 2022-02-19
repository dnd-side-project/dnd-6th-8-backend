package com.travel.domain.scrap.dto;
import com.travel.domain.archive.entity.Archives;
import com.travel.domain.scrap.entity.Scraps;
import com.travel.domain.user.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@ApiModel(value = "스크랩 목록")
public class ScrapPreviewDto {
    @ApiModelProperty(value = "스크랩 id", example = "1")
    private Long id;

    @ApiModelProperty(value = "스크랩한 시간", example = "2022-02-19T22:24:00")
    private LocalDateTime scrapDateTime = LocalDateTime.now();

    @ApiModelProperty(value = "스크랩한 아카이브", example = "대충 다녀도 아름다운 제주")
    private Archives archives;

    @ApiModelProperty(value = "스크랩한 유저")
    private User user;

    public ScrapPreviewDto(Scraps entity) {
        this.id = entity.getId();
        this.archives = entity.getArchives();
        this.scrapDateTime = entity.getScrapDateTime();
        this.user = entity.getUser();
    }
}

