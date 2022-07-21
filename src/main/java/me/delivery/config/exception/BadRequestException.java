package me.delivery.config.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestException extends BaseException {
    private ErrorCode errorCode;

    public BadRequestException(ErrorCode errorCode) {
        super(errorCode);
    }
}
