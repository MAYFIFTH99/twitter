package mayfifth99.twitter.common.ui;

import mayfifth99.twitter.common.exception.ErrorCode;

public class Response<T> {


    private final Integer code;
    private final String message;
    private final T value;

    public static<T> Response<T> ok(T value){
        return new Response<>(200,"ok", value);
    }

    public static<T> Response<T> error(ErrorCode error) {
        return new Response<>(error.getCode(), error.getMessage(), null);
    }

    public Response(Integer code, String message, T value) {
        this.code = code;
        this.message = message;
        this.value = value;
    }
}
