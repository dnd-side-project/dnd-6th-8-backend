//package com.travel.domain.scrap.dto;
//
//import com.fasterxml.jackson.annotation.JsonCreator;
//import com.fasterxml.jackson.annotation.JsonProperty;
//import com.travel.domain.archive.entity.Archives;
//import com.travel.domain.scrap.entity.Scraps;
//import com.travel.domain.user.entity.User;
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.Setter;
//import org.apache.tomcat.jni.Local;
//import org.springframework.format.annotation.DateTimeFormat;
//
//import java.time.LocalDateTime;
//
//@Getter
//@Setter
//@ApiModel(value = "스크랩 저장하기")
//public class ScrapsSaveRequestDto {
//
//
//    @Builder
//    public ScrapsSaveRequestDto(LocalDateTime scrapDateTime, ) {
//        this.scrapDateTime = scrapDateTime;
//        this.user = user;
//    }
//
//    public Scraps toEntity() {
//        return Scraps.builder().scrapDateTime(scrapDateTime).user(user).build();
//    }
//}