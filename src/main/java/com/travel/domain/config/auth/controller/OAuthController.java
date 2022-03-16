//package com.travel.domain.config.auth.controller;
//
//import com.travel.domain.config.auth.SocialLoginType;
//import com.travel.domain.config.auth.service.OAuthService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import lombok.AllArgsConstructor;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.*;
//import org.springframework.stereotype.Component;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.MultiValueMap;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.client.RestClientException;
//import org.springframework.web.client.RestTemplate;
//
////@RestController
////@RequiredArgsConstructor
////@Component
////@AllArgsConstructor
////@Api(tags = {"사용자인증 API"})
////public class OAuthController {
//
////    @ResponseBody
////    @GetMapping("/kakao")
////    public void kakaoCallback(@RequestParam String code) {
////        System.out.println(code);
////        String access_Token = kakao.getKakaoAccessToken(code);
////        System.out.println("controller access_token : " + access_Token);
////    }
//
////    private final RestTemplate restTemplate;
////
////    @Value("${security.oauth2.client.registration.kakao.clientId}")
////    private String kakaoOauth2ClinetId;
////
////    private String grantType= "authorization_code";
////
////    @Value("${security.oauth2.client.registration.kakao.tokenRedirectUri}")
////    private String frontendRedirectUrl;
////
////    @Value("${security.oauth2.client.registration.kakao.clientSecret}")
////    private String kakaoOauth2ClinetSecret;
////
////
////    @ApiOperation(value = "카카오 인증토큰발급")
////    @GetMapping("/auth/kakao")
////    public String kakaoTokenApi(String code) {
////        HttpHeaders headers = new HttpHeaders();
////        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
////
////        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
////        params.add("grant_type", grantType);
////        params.add("client_id", kakaoOauth2ClinetId);
////        params.add("redirect_uri", frontendRedirectUrl + "/callback/kakao");
////        params.add("code", code);
////        params.add("client_secret",kakaoOauth2ClinetSecret);
////
////        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
////        String url = "https://kauth.kakao.com/oauth/token";
////        try {
////            ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
//////            AuthorizationKakao authorization = objectMapper.readValue(response.getBody(), AuthorizationKakao.class);
////
////            System.out.println(response);
////            return response.toString();
////        } catch (RestClientException ex) {
////            ex.printStackTrace();
//////            throw new ProcyanException(E00001);
//////            throw new BaseException(DATABASE_ERROR);
////        }
////        return "failed";
////
////    }
//
//    @RestController
//    @Api(tags = {"사용자인증 API"})
//    @RequestMapping(value = "/auth")
//    @RequiredArgsConstructor
//    public class OAuthController {
//        private final OAuthService oauthService;
//
//        /**
//         * 사용자로부터 SNS 로그인 요청을 Social Login Type 을 받아 처리
//         * @param socialLoginType (GOOGLE, KAKAO)
//         */
//        @GetMapping(value = "/{socialLoginType}")
//        public ResponseEntity<String> socialLoginType(@PathVariable(name = "socialLoginType") SocialLoginType socialLoginType) {
//            System.out.println(socialLoginType);
//            return new ResponseEntity<String>(oauthService.request(socialLoginType),
//                    HttpStatus.OK);
//        }
//
//        /**
//         * Social Login API Server 요청에 의한 callback 을 처리
//         * @param socialLoginType (GOOGLE, KAKAO)
//         * @param code API Server 로부터 넘어오는 code
//         * @return SNS Login 요청 결과로 받은 Json 형태의 String 문자열 (access_token, refresh_token 등)
//         */
//        @GetMapping(value = "/{socialLoginType}/callback")
//        public String getAccessToken(
//                @PathVariable(name = "socialLoginType") SocialLoginType socialLoginType,
//                @RequestParam(name = "code") String code) {
////            log.info(">> 로그인 code :: {}", code);
//            System.out.println(">>로그인 code :: {}"+ code);
//            return oauthService.requestAccessToken(socialLoginType, code);
//        }
//    }
//
