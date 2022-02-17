//package com.travel.domain.config.auth.service;
//
//import com.travel.domain.config.auth.SocialLoginType;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//@Slf4j
//public class OAuthService {
//    private final List<SocialOauth> socialOauthList;
//    private final HttpServletResponse response;
//
//    public String request(SocialLoginType socialLoginType) {
//        SocialOauth socialOauth = this.findSocialOauthByType(socialLoginType);
//        System.out.println(socialOauth);
//        String redirectURL = socialOauth.getOauthRedirectURL();
//        System.out.println("\n>>>url:::");
//        System.out.println(redirectURL);
//        System.out.println(">>>url:::\n");
//
//        return redirectURL;
//    }
//
//    public String requestAccessToken(SocialLoginType socialLoginType, String code) {
//        SocialOauth socialOauth = this.findSocialOauthByType(socialLoginType);
//        System.out.println(">>service");
//        return socialOauth.requestAccessToken(code);
//    }
//
//    private SocialOauth findSocialOauthByType(SocialLoginType socialLoginType) {
//        return socialOauthList.stream()
//                .filter(x -> x.type() == socialLoginType)
//                .findFirst()
//                .orElseThrow(() -> new IllegalArgumentException("알 수 없는 socialType"));
//    }
//}
