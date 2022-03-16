package com.travel.domain.config.auth;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;

import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@Component
@AllArgsConstructor
@Api(tags = {"사용자인증 API"})
@RequestMapping("/auth")
public class OAuthController {

    //여기서 접근 url던져준다.
    private String kakaoUrl = "http://3.37.253.113:8080/oauth2/authorization/kakao";
    private String naverUrl = "";
    private String googleUrl = "";

    @ResponseBody
    @GetMapping("/kakao")
    public ResponseEntity<String> kakaoLogin() {
        return ResponseEntity.ok(kakaoUrl);
    }

    @ResponseBody
    @GetMapping("/naver")
    public ResponseEntity<String> naverLogin() {
        return ResponseEntity.ok(naverUrl);
    }

    @ResponseBody
    @GetMapping("/google")
    public ResponseEntity<String> googleLogin() {
        return ResponseEntity.ok(googleUrl);
    }
}


