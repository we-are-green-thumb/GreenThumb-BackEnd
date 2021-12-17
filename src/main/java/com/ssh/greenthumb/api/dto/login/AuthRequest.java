package com.ssh.greenthumb.api.dto.login;

import lombok.Getter;

public class AuthRequest {

    @Getter
    public static class Login {
        private String email;
        private String password;
    }

}
