package com.ssh.greenthumb.api.dto.login;

import lombok.Getter;

public class AuthRequest {

    @Getter
    public static class Login {
        private String email;
        private String password;
    }

    @Getter
    public static class Logout {
        private String email;
    }
//
//    @Builder
//    public LoginRequest(String email, String password) {
//        this.email = email;
//        this.password = password;
//    }

}
