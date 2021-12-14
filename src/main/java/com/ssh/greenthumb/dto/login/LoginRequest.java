package com.ssh.greenthumb.dto.login;

import lombok.Builder;
import lombok.Getter;

@Getter
public class LoginRequest {

    private String userName;
    private String password;

    @Builder
    public LoginRequest(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

}
