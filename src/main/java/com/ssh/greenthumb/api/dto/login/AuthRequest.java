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

    @Getter
    public static class OAuth {
        private String email;
        private String idToken;
    }

}
