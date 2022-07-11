package com.travel.domain.emoji.dto;

import com.travel.domain.archive.entity.Archives;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
@ApiModel(value = "이모지 응답")
public class EmojiListResponseDto {


//    @ApiModelProperty(value = "이모지 이름", example = "좋아요")
//    private Map<String, Object> emojis = new HashMap<String, Object>();

    public EmojiListResponseDto(Archives entity){
    }
}
