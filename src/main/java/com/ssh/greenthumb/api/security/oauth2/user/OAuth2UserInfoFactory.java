package com.ssh.greenthumb.api.security.oauth2.user;

import java.util.Map;

public class OAuth2UserInfoFactory {

    public static OAuth2UserInfo getOAuth2UserInfo(String authProvider, Map<String, Object> attributes) {
//        if(registrationId.equalsIgnoreCase(AuthProvider.google.toString())) {
//            return new GoogleOAuth2UserInfo(attributes);
//        } else if(registrationId.equalsIgnoreCase(AuthProvider.naver.toString())) {
//            return new NaverOAuth2UserInfo(attributes);
//        } else if(registrationId.equalsIgnoreCase(AuthProvider.kakao.toString())) {
//            return new KakaoOAuth2UserInfo(attributes);
//        } else {
//            throw new OAuth2AuthenticationProcessingException(registrationId + "로그인은 지원하지 않습니다.");
//        }
//        String GOOGLE = AuthProvider.google.toString();
//        String naver = AuthProvider.naver.toString();
//        String kakao = AuthProvider.kakao.toString();
//        String local = AuthProvider.local.toString();

        switch (authProvider) {
            case "google": return new GoogleOAuth2UserInfo(attributes);
            case "naver": return new NaverOAuth2UserInfo(attributes);
            case "kakao": return new KakaoOAuth2UserInfo(attributes);
            case "local": return new KakaoOAuth2UserInfo(attributes);
//            case LOCAL: return new User(attributes);
            default: throw new IllegalArgumentException("Invalid Provider Type.");
        }
    }

}
