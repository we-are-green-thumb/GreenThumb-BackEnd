package kr.pe.greenthumb.common.exception;

public class NotNullException extends CommonException {

    private static final String MESSAGE = "필수 입력값ㅇ.";

    public NotNullException() {
        super(MESSAGE);
    }

}
