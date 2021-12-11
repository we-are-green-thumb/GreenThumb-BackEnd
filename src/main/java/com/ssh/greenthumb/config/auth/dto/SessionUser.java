package com.ssh.greenthumb.config.auth.dto;

import com.ssh.greenthumb.domain.login.OAuthUser;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String imageUrl;

    @Builder
    public SessionUser(OAuthUser user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.imageUrl = user.getImageUrl();
    }
}
