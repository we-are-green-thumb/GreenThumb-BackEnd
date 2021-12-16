package com.ssh.greenthumb.auth.token;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

@Getter
@ConfigurationProperties(prefix = "app")
public class AppProperties {

    private final Auth auth = new Auth();
    private final OAuth2 oAuth2 = new OAuth2();

    @Getter
    @Setter
    @RequiredArgsConstructor
    public static class Auth {
        @Value("${app.auth.tokenSecret}")
        private String tokenSecret;
        @Value("${app.auth.accessTokenExpiry}")
        private long accessTokenExpiry;
        @Value("${app.auth.refreshTokenExpiry}")
        private long refreshTokenExpiry;

        @Builder
        public Auth(String tokenSecret, long accessTokenExpiry, long refreshTokenExpiry) {
            this.tokenSecret = tokenSecret;
            this.accessTokenExpiry = accessTokenExpiry;
            this.refreshTokenExpiry = refreshTokenExpiry;
        }
    }

    @Getter
    @RequiredArgsConstructor
    public static final class OAuth2 {
        @Value("${app.oauth2.authorizedRedirectUris}")
        private List<String> authorizedRedirectUris = new ArrayList<>();

        public OAuth2 authorizedRedirectUris(List<String> authorizedRedirectUris) {
            this.authorizedRedirectUris = authorizedRedirectUris;
            return this;
        }
    }

}