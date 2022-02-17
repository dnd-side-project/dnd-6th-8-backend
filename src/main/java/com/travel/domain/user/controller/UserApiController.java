package com.travel.domain.user.controller;


import com.travel.domain.user.dto.SurveySaveRequestDto;
import com.travel.domain.user.dto.UserSaveRequestDto;
import com.travel.domain.user.entity.User;
import com.travel.domain.user.service.SurveyService;
import com.travel.domain.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Api(tags = {"사용자 API 정보를 제공"})
public class UserApiController {

    private final UserService userService;
    private final SurveyService surveyService;

    @GetMapping("/user")
    public String userInfo(){
        return "";
    }

    @ApiOperation(value = "사용자 생성 임시 API(소셜 로그인 구현 후 바꿀 예정)")
    @PostMapping("/user")
    public ResponseEntity<Void> createUser(@RequestParam String userName){
//        UUID saveId = userService.save(new UserSaveRequestDto(userName));
        int saveId = userService.save(new UserSaveRequestDto(userName));
        return ResponseEntity.created(URI.create("/api/v1/user/")).build();
    }

    @ApiOperation(value = "사용자 서베이 저장 API")
    @PostMapping("/user/survey")
    public ResponseEntity<Void> saveSurveyInfo(@RequestBody SurveySaveRequestDto surveySaveRequestDto){
        Long saveId = surveyService.save(surveySaveRequestDto);
        return ResponseEntity.created(URI.create("/api/v1/user/survey/" + saveId)).build();
    }

    @GetMapping("/user/achives")
    public String getUserArchive(){
        return "";
    }


}
