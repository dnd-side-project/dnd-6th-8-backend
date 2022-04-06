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
    private final String user;

    private LocalDateTime scrapDateTime() {
        return scrapDateTime;
    }
    private String user() { return user; }

    @JsonCreator
    public ScrapsSaveRequestDto(
            @JsonProperty("scrapDateTime") LocalDateTime scrapDateTime,
            @JsonProperty("user") String user) {
        this.scrapDateTime = scrapDateTime;
        this.user = user;
    }

//    public Scraps toEntity() {
//        return Scraps.builder().scrapDateTime(scrapDateTime).user(user).build();
//    }
}