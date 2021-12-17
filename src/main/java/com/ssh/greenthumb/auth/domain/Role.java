package com.ssh.greenthumb.auth.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum Role {

    DELETE("ROLE_DELETE", "삭제회원"),
    BLACK("ROLE_BLACK", "블랙 회원"),
    USER("ROLE_USER", "일반 회원"),
    ADMIN("ROLE_ADMIN", "관리자");

    private final String code;
    private final String displayName;

    public static Role of(String code) {
        return Arrays.stream(Role.values())
                .filter(r -> r.getCode().equals(code))
                .findAny()
                .orElse(USER);
    }

}
