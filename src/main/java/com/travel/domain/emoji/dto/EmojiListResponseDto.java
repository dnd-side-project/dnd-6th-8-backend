package com.travel.domain.emoji.dto;

import com.travel.domain.archive.entity.Archives;
import com.travel.domain.emoji.entity.Emoji;
import com.travel.domain.emoji.entity.UserEmojiSelected;
import com.travel.domain.emoji.repository.EmojiRepository;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@ApiModel(value = "이모지 응답")
public class EmojiListResponseDto {

//    private HashMap<Long, List> emojisMap = new HashMap<>();

    private Long emojiId;
    private Long emojiCount;
    private String emojisName;
    private String emojisUrl;
    private Boolean emojisChecked;

    public EmojiListResponseDto(HashMap<String, String> emojisMap){
        this.emojiId = Long.parseLong(emojisMap.get("emojiId"));
        this.emojiCount = Long.parseLong(emojisMap.get("emojiCount"));
        this.emojisName = emojisMap.get("emojisName");
        this.emojisUrl = emojisMap.get("emojisURL");
        this.emojisChecked = Boolean.parseBoolean(emojisMap.get("emojisChecked"));
    }

    public static List<EmojiListResponseDto> listOf(List<HashMap> emojisList) {
        List<EmojiListResponseDto> emojiListResponses = new ArrayList<>();

        for (HashMap<String, String> emojis : emojisList){
            emojiListResponses.add(new EmojiListResponseDto(emojis));
        }

        return emojiListResponses;
    }
}
