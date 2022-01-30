package com.travel.domain.scrap.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
@Api(tags = {"스크랩 API 정보를 제공"})
public class ScrapApiController {

    @ApiOperation(value = "스크랩을 저장하는 api")
    @PostMapping("/scraps")
    public String addScrap(){return "";}

    @GetMapping("/scraps")
    public String getScrap(){return "";}

}
