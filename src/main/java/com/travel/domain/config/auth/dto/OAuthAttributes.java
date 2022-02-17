//package com.travel.domain.config.auth.dto;
//
//
//import com.travel.domain.user.entity.User;
//import lombok.Builder;
//import lombok.Getter;
//
//import javax.management.relation.Role;
//import java.util.Map;
//
//import static com.travel.domain.user.entity.Role.GUEST;
//
//@Getter
//public class OAuthAttributes {
//    private Map<String, Object> attributes; // OAuth2 반환하는 유저 정보 Map
//    private String nameAttributeKey;
//    private String name;
//    private String email;
//    private String picture;
//
//    @Builder
//    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email) {
//        this.attributes = attributes;
//        this.nameAttributeKey = nameAttributeKey;
//        this.name = name;
//        this.email = email;
////        this.picture = picture;
//    }
//
//    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes){
//        // 여기서 네이버와 카카오 등 구분 (ofNaver, ofKakao)
//        if("kakao".equals(registrationId)){
//            return ofKakao("id", attributes);
//        }
//
//        return ofGoogle(userNameAttributeName, attributes);
//    }
//
//    private static OAuthAttributes ofKakao(String userNameAttributeName, Map<String, Object> attributes) {
//        // kakao는 kakao_account에 유저정보가 있다. (email)
//        Map<String, Object> kakaoAccount = (Map<String, Object>)attributes.get("kakao_account");
//        // kakao_account안에 또 profile이라는 JSON객체가 있다. (nickname, profile_image)
//        Map<String, Object> kakaoProfile = (Map<String, Object>)kakaoAccount.get("profile");
//
//        return OAuthAttributes.builder()
//                .name((String) kakaoProfile.get("nickname"))
//                .email((String) kakaoAccount.get("email"))
////                .picture((String) kakaoProfile.get("profile_image_url"))
//                .attributes(attributes)
//                .nameAttributeKey(userNameAttributeName)
//                .build();
//    }
//
//    private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
//        return OAuthAttributes.builder()
//                .name((String) attributes.get("name"))
//                .email((String) attributes.get("email"))
////                .picture((String) attributes.get("picture"))
//                .attributes(attributes)
//                .nameAttributeKey(userNameAttributeName)
//                .build();
//    }
//
//    public User toEntity(){
//        return User.builder()
//                .userName(name)
//                .email(email)
//                .role(GUEST) // 기본 권한 GUEST
//                .build();
//    }
//
//}
