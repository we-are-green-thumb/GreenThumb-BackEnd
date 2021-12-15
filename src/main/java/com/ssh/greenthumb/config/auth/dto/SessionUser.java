package com.ssh.greenthumb.config.auth.dto;

import com.ssh.greenthumb.api.domain.user.User;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String nickName;
    private String email;
    private String imageUrl;

    @Builder
    public SessionUser(User user) {
        this.nickName = user.getNickName();
        this.email = user.getEmail();
        this.imageUrl = user.getImageUrl();
    }
}
