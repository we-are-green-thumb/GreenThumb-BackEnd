package com.ssh.greenthumb.api.dto.login;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SignUpRequest {

    private String email;
    private String nickName;
    private String password;

    @Builder
    public SignUpRequest(String email, String nickName, String password) {
        this.email = email;
        this.nickName = nickName;
        this.password = password;
    }

}
