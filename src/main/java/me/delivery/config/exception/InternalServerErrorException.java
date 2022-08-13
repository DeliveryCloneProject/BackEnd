package me.delivery.config.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalServerErrorException extends BaseException {
    private ErrorCode errorCode;

    public InternalServerErrorException(ErrorCode errorCode) {
        super(errorCode);
    }
}
