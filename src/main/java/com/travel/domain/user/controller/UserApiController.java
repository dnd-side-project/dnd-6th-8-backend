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
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.security.Principal;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Api(tags = {"사용자 API 정보를 제공"})
public class UserApiController {

    private final UserService userService;
    private final SurveyService surveyService;

    @ApiOperation(value = "사용자 정보 가져오기")
    @GetMapping("/user")
    public ResponseEntity<String> getUserInfo(Principal principal){
        return ResponseEntity.ok(principal.getName());
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