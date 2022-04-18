package com.travel.domain.sticker.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Api(tags = {"이모션 스티커 정보를 제공"})
public class StickerApiController {
    @GetMapping("/day/sticker")
    public String addSticker() {return "";}
}
