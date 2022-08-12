package com.travel.domain.archive.dto;

import com.travel.domain.archive.entity.Archives;
import com.travel.domain.archive.entity.EArchivingStyle;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Getter
@ApiModel(value = "자세한 아카이브")
public class ArchiveResponseDto {

    @ApiModelProperty(value = "아카이브 id", example = "1")
    private Long id;

    @ApiModelProperty(value = "아카이브 제목", example = "대충 다녀도 아름다운 제주")
    private String title;

    private String travelDuration;

    @ApiModelProperty(value = "출발 날짜", example = "2021-12-10")
    private LocalDate createdAt;

    @ApiModelProperty(value = "기록 스타일", example = "감성")
    private EArchivingStyle archivingStyle;

    @ApiModelProperty(value = "여행 장소", example = "부산")
    private String places;

    private String coverImage;

    private int scrapNum;

    private int emojiNum;

    private String shortContent;


    public ArchiveResponseDto(Archives entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.travelDuration = calculateDuration(entity);
        this.archivingStyle = entity.getArchivingStyle();
        this.places = entity.getPlace().getName();
        this.coverImage = entity.getCoverImage();
        this.scrapNum = entity.getScraps().size();
        this.emojiNum = entity.getUserEmojiSelected().size();
        this.createdAt = entity.getCreatedAt().toLocalDate();
//        this.shortContent = entity.get
    }

    public static String calculateDuration(Archives entity) {
        if(entity.getFirstDay() == null || entity.getLastDay() == null){
            return "0일";
        }
        Period period = Period.between(entity.getFirstDay(), entity.getLastDay());
        int end = period.getDays() + 1;
        String result = period.getDays() + "박" + end + "일";
        return result;
    }

    public static List<ArchiveResponseDto> listOf(List<Archives> filtered) {
        List<ArchiveResponseDto> archiveResponses = new ArrayList<>();

        for (Archives archive : filtered) {
            archiveResponses.add(new ArchiveResponseDto(archive));
        }

        return archiveResponses;
    }
}
