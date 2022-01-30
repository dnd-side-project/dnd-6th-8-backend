package com.travel.domain.user.controller;

import com.travel.domain.archive.dto.ArchivesSaveRequestDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Api(tags = {"게시글 API 정보를 제공"})
public class UserApiController {

    @GetMapping("/user")
    public String userInfo(){
        return "";
    }

    @PostMapping("/user/survey/update")
    public String updateSuveyInfo(){
        return "";
    }

    @GetMapping("/user/achives")
    public String getUserArchive(){
        return "";
    }



}
