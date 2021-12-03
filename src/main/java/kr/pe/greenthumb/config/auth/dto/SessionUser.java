package kr.pe.greenthumb.config.auth.dto;

import kr.pe.greenthumb.domain.login.OAuth2User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;

    public SessionUser(OAuth2User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
