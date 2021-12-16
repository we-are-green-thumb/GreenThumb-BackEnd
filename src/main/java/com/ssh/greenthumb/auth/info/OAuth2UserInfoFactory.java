package com.ssh.greenthumb.auth.info;

import com.ssh.greenthumb.auth.info.impl.GoogleOAuth2UserInfo;
import com.ssh.greenthumb.auth.info.impl.KakaoOAuth2UserInfo;
import com.ssh.greenthumb.auth.info.impl.NaverOAuth2UserInfo;

import java.util.Map;

public class OAuth2UserInfoFactory {

    public static OAuth2UserInfo getOAuth2UserInfo(String authProvider, Map<String, Object> attributes) {
        switch (authProvider) {
            case "google": return new GoogleOAuth2UserInfo(attributes);
            case "naver": return new NaverOAuth2UserInfo(attributes);
            case "kakao": return new KakaoOAuth2UserInfo(attributes);
            default: throw new IllegalArgumentException("Invalid Provider Type.");
        }
    }

}
