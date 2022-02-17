//package com.travel.domain.config.auth.service;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.google.gson.Gson;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.*;
//import org.springframework.http.client.ClientHttpResponse;
//import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
//import org.springframework.stereotype.Component;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.MultiValueMap;
//import org.springframework.web.client.DefaultResponseErrorHandler;
//import org.springframework.web.client.RestClientException;
//import org.springframework.web.client.RestTemplate;
//
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//@Component
//@RequiredArgsConstructor
//public class KakaoOauth implements SocialOauth{
//
//     @Value("${spring.security.oauth2.client.registration.kakao.url.login}")
//     private String KAKAO_SNS_BASE_URL;
//
//     @Value("${spring.security.oauth2.client.registration.kakao.clientId}")
//     private String KAKAO_SNS_CLIENT_ID;
//
//     @Value("${spring.security.oauth2.client.registration.kakao.url.callback}")
//     private String KAKAO_SNS_CALLBACK_URL;
//
//     @Value("${spring.security.oauth2.client.registration.kakao.clientSecret}")
//     private String KAKAO_SNS_CLIENT_SECRET;
//
//     @Value("${spring.security.oauth2.client.registration.kakao.url.token}")
//     private String KAKAO_SNS_TOKEN_BASE_URL;
//
//
////    private String KAKAO_SNS_BASE_URL = "https://kauth.kakao.com/oauth/authorize";
////    private String KAKAO_SNS_CLIENT_ID = "a33afbe682420249e0f92f9fa81af07d";
////    private String KAKAO_SNS_CALLBACK_URL = "http://localhost:8080/loginservice/auth/kakao/callback";
////    //private String KAKAO_SNS_CLIENT_SECRET;
////    private String KAKAO_SNS_TOKEN_BASE_URL = "https://kauth.kakao.com/oauth/token";
////    private String KAKAO_SNS_PROFILE_BASE_URL = "https://kapi.kakao.com/v2/user/me";
//
//    @Override
//    public String getOauthRedirectURL() {
//        StringBuilder sb = new StringBuilder();
//        sb.append(KAKAO_SNS_BASE_URL + "?");
//        sb.append("client_id=" + KAKAO_SNS_CLIENT_ID);
//        sb.append("&redirect_uri="+KAKAO_SNS_CALLBACK_URL);
//        sb.append("&response_type=code");
//        String url = sb.toString();
////        Map<String, Object> params = new HashMap<>();
//////        params.put("scope", "profile");
////        params.put("response_type", "code");
////        params.put("client_id", KAKAO_SNS_CLIENT_ID);
////        params.put("redirect_uri", KAKAO_SNS_CALLBACK_URL);
////
////
////        String parameterString = params.entrySet().stream()
////                .map(x -> x.getKey() + "=" + x.getValue())
////                .collect(Collectors.joining("&"));
//
//        return url;
//    }
//
//    @Override
//    public String requestAccessToken(String code) {
//
//        RestTemplate restTemplate = new RestTemplate();
//        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
//        restTemplate.setErrorHandler(new DefaultResponseErrorHandler(){
//            public boolean hasError(ClientHttpResponse response) throws IOException {
//                HttpStatus statusCode = response.getStatusCode();
//                return statusCode.series() == HttpStatus.Series.SERVER_ERROR;
//            }
//        });
//
//        Gson gson = new Gson();
//        HttpHeaders headers = new HttpHeaders();
////        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//        headers.add("Accept", "application/json");
//        // Set parameter
//        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//        params.add("grant_type", "authorization_code");
//        params.add("client_id", KAKAO_SNS_CLIENT_ID);
//        params.add("redirect_uri",KAKAO_SNS_CALLBACK_URL);
//        params.add("code", code);
//        params.add("client_secret",KAKAO_SNS_CLIENT_SECRET);
//        // Set http entity
//        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
//        System.out.println(request);
//        try {
//            String tokenUrl = KAKAO_SNS_TOKEN_BASE_URL;
//            System.out.println("here");
//            ResponseEntity<String> response = restTemplate.postForEntity(tokenUrl, request, String.class);
//
//            System.out.println("\n>>>> getKakaoAccessToken");
//            System.out.println(response.getBody());
//            System.out.println("\n");
//
//            if (response.getStatusCode() == HttpStatus.OK) {
//                String str = response.getBody();
//                ObjectMapper mapper = new ObjectMapper();
//                Map<String, Object> map = mapper.readValue(str, Map.class);
//                Object obj = map.get("access_token");
//                String resString = String.valueOf(obj);
//                System.out.println("\n accessToken");
//                System.out.println(resString);
//                //카카오에서 개인정보 가져오기 (이름 이멜)
////                getProfile(resString);
//
//                return response.getBody();
//
//            }
//        } catch (RestClientException | JsonProcessingException ex) {
//            ex.printStackTrace();
//        }
//        return "카카오 로그인 요청 처리 실패";
//    }
////
////    public void getProfile(String accessToken) {
////        RestTemplate restTemplate = new RestTemplate();
////        Gson gson = new Gson();
////        HttpHeaders headers = new HttpHeaders();
////        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
////        headers.set("Authorization", "Bearer " + accessToken);
////
////        // Set http entity
////        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(null, headers);
////        try {
////            // Request profile
//////            String url = KAKAO_SNS_PROFILE_BASE_URL;
////            ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
////            System.out.println("\n>>>>getKakaoProfile");
////            System.out.println(response.getBody());
////            System.out.println("\n");
////            String str = response.getBody();
////            System.out.println(str);
////
////            // ObjectMapper mapper = new ObjectMapper();
////            // Map<String, Object> map = mapper.readValue(str, Map.class);
////            // Object obj = map.get("kakao_account");
////            // String resString = String.valueOf(obj);
////
////            // if (response.getStatusCode() == HttpStatus.OK)
////            //  return gson.fromJson(response.getBody(), UserInfo.class);
////
////
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
////        //  return null;
////    }
//}
