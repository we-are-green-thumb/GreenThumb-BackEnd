package com.ssh.greenthumb.dto.login;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SignUpRequest {

    private String name;
    private String userName;
    private String password;

    @Builder
    public SignUpRequest(String name, String userName, String password) {
        this.name = name;
        this.userName = userName;
        this.password = password;
    }

}
