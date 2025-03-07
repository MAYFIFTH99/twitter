package mayfifth99.twitter.common.ui;

import lombok.extern.slf4j.Slf4j;
import mayfifth99.twitter.common.exception.ErrorCode;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public Response<Void> handleIllegalArgumentException(IllegalArgumentException e){
        return Response.error(ErrorCode.INVALID_INPUT_VALUE);
    }

    @ExceptionHandler(Exception.class)
    public Response<Void> exception(Exception e){
        log.error(e.getMessage());
        return Response.error(ErrorCode.INTERNAL_SERVER_ERROR);
    }

}
