package mayfifth99.twitter.common.ui;

import mayfifth99.twitter.common.exception.ErrorCode;

public record Response<T>(Integer code, String message, T value) {


    public static <T> Response<T> ok(T value) {
        return new Response<>(200, "ok", value);
    }

    public static <T> Response<T> error(ErrorCode error) {
        return new Response<>(error.getCode(), error.getMessage(), null);
    }

}
