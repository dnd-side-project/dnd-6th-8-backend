package com.travel.domain.user.controller;


import com.travel.domain.archive.dto.ArchiveResponseDto;
import com.travel.domain.user.dto.SurveySaveRequestDto;
import com.travel.domain.user.dto.UserSaveRequestDto;
import com.travel.domain.user.entity.User;
import com.travel.domain.user.service.SurveyService;
import com.travel.domain.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.net.URI;
import java.security.Principal;
import java.util.List;
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
    public ResponseEntity<String> getUserInfo(@ApiIgnore Principal principal){
        return ResponseEntity.ok(principal.getName());
    }

    @ApiOperation(value = "사용자 서베이 저장 API")
    @PostMapping("/user/survey")
    public ResponseEntity<Void> saveSurveyInfo(@RequestBody SurveySaveRequestDto surveySaveRequestDto, @ApiIgnore Principal principal){
        surveyService.save(surveySaveRequestDto, principal.getName());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user/achives")
    public String getUserArchive(){
        return "";
    }

    @ApiOperation(value = "사용자 게시물 공유한 필터링 API")
    @GetMapping("my/archives/places/on")
    public ResponseEntity<List<ArchiveResponseDto>> getArchiveListByShare(@RequestParam boolean isShare
            , @ApiIgnore Principal principal){

        List<ArchiveResponseDto> archivesResponseDtos =  userService.findArchiveByShare(isShare, principal.getName());
        return ResponseEntity.ok(archivesResponseDtos);
    }
}
