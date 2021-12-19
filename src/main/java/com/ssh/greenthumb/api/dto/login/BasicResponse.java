package com.ssh.greenthumb.api.dto.login;

import lombok.Builder;
import lombok.Getter;
@Getter
public class BasicResponse {

    private boolean success;
    private String message;

    @Builder
    public BasicResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

}
