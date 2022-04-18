package com.travel.domain.emoji.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@ApiModel(value = "이모지 응답")
public class EmojiResponse {

    @ApiModelProperty(value = "이모지 아이디", example = "1")
    private Long id;

    @ApiModelProperty(value = "이모지 이름", example = "좋아요")
    private String emoji;
}
