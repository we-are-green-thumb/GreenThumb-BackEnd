package kr.pe.greenthumb.common.exception;

import lombok.Getter;

@Getter
public class NotFoundException extends CommonException {

    private static final String MESSAGE = "존재하지 않는 정보입니다.";

    public NotFoundException() {
        super(MESSAGE);
    }

}
