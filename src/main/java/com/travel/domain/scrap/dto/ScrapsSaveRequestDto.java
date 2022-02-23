package com.travel.domain.scrap.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.travel.domain.archive.entity.Archives;
import com.travel.domain.scrap.entity.Scraps;
import com.travel.domain.user.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import org.apache.tomcat.jni.Local;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@ApiModel(value = "스크랩 저장하기")
public class ScrapsSaveRequestDto {
    private final LocalDateTime scrapDateTime;
    private final User user;

    private LocalDateTime scrapDateTime() {
        return scrapDateTime;
    }
    private User user() { return user; }

    @JsonCreator
    public ScrapsSaveRequestDto(
            @JsonProperty("scrapDateTime") LocalDateTime scrapDateTime,
            @JsonProperty("user") User user) {
        this.scrapDateTime = scrapDateTime;
        this.user = user;
    }


//    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
//    @ApiModelProperty(value = "스크랩한 시간", example = "2022-02-19T22:24:00")
//    private LocalDateTime scrapDateTime;

//    @ApiModelProperty(value = "스크랩한 유저")
//    private User user;

//    @Builder
//    public ScrapsSaveRequestDto(LocalDateTime scrapDateTime) {
//        this.scrapDateTime = scrapDateTime;
////        this.user = user;
//    }

    public Scraps toEntity() {
        return Scraps.builder().scrapDateTime(scrapDateTime).user(user).build();
    }
}