package com.travel.domain.scrap.dto;
import com.travel.domain.archive.entity.EArchivingStyle;
import com.travel.domain.scrap.entity.Scraps;
import io.swagger.annotations.ApiModel;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@ApiModel(value = "스크랩 목록")
public class ScrapPreviewDto {
    private Long id;

    private Long archiveId;

    private String archiveTitle;

    private EArchivingStyle archivingStyle;

    private String coverImage;

    private LocalDateTime scrapedAt;

    private CountMyScrapsDto countMyScraps;

    public ScrapPreviewDto(Scraps entity) {
        this.id = entity.getId();
        this.archiveId = entity.getArchives().getId();
        this.archiveTitle = entity.getArchives().getTitle();
        this.archivingStyle = entity.getArchives().getArchivingStyle();
        this.coverImage = entity.getArchives().getCoverImage();
        this.scrapedAt = entity.getCreatedAt();
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
