package com.travel.domain.emoji.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Api(tags = {"이모지 정보를 제공"})
public class EmojiApiController {

    @PostMapping("/archive/emoji")
    public String addEmoji(){
        return "";
    }
}
