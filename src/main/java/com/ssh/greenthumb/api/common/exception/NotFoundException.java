package com.ssh.greenthumb.api.common.exception;

import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException {

    private static final String MESSAGE = "존재하지 않는 정보.";

    public NotFoundException() {
        super(MESSAGE);
    }

}
