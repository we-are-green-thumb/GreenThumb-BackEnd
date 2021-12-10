package kr.pe.greenthumb.dto.login;

import lombok.Builder;
import lombok.Getter;

@Getter
public class AuthResponse {

    private String accessToken;
    private String tokenType = "Bearer";  // 인증 방식

    @Builder
    public AuthResponse(String accessToken) {
        this.accessToken = accessToken;
    }

}
