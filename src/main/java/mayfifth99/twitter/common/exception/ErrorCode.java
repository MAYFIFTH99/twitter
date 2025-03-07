package mayfifth99.twitter.common.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    INVALID_INPUT_VALUE(400, "invalid input value 입니다."),
    NOT_FOUND(404, "not found 입니다."),
    INTERNAL_SERVER_ERROR(500, "internal server error 입니다.");

    private final Integer code;
    private final String message;

    ErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
