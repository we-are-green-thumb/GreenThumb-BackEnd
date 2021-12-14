package com.ssh.greenthumb.security.oauth2.user;

import com.ssh.greenthumb.domain.login.AuthProvider;

import java.util.Map;

public class OAuth2UserInfoFactory {

    public static OAuth2UserInfo getOAuth2UserInfo(AuthProvider authProvider, Map<String, Object> attributes) {
//        if(registrationId.equalsIgnoreCase(AuthProvider.google.toString())) {
//            return new GoogleOAuth2UserInfo(attributes);
//        } else if(registrationId.equalsIgnoreCase(AuthProvider.naver.toString())) {
//            return new NaverOAuth2UserInfo(attributes);
//        } else if(registrationId.equalsIgnoreCase(AuthProvider.kakao.toString())) {
//            return new KakaoOAuth2UserInfo(attributes);
//        } else {
//            throw new OAuth2AuthenticationProcessingException(registrationId + "로그인은 지원하지 않습니다.");
//        }

        switch (authProvider) {
            case GOOGLE: return new GoogleOAuth2UserInfo(attributes);
            case NAVER: return new NaverOAuth2UserInfo(attributes);
            case KAKAO: return new KakaoOAuth2UserInfo(attributes);
            case LOCAL: return new OAuth2UserInfo(attributes) {
                @Override
                public String getId() {
                    return null;
                }

                @Override
                public String getName() {
                    return null;
                }

                @Override
                public String getEmail() {
                    return null;
                }

                @Override
                public String getImageUrl() {
                    return null;
                }
            };
            default: throw new IllegalArgumentException("Invalid Provider Type.");
        }
    }

}
