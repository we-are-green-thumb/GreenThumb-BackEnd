package com.ssh.greenthumb.api.dto.login;

import lombok.Builder;
import lombok.Getter;

@Getter
public class AuthResponse {

    private String tokenType = "Bearer";
    private String accessToken;
    private Long id;

    @Builder
    public AuthResponse(String accessToken, String refreshToken, Long id) {
        this.accessToken = accessToken;
        this.id = id;
    }

}