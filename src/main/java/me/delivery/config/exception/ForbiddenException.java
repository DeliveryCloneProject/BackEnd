package me.delivery.config.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class ForbiddenException extends BaseException {
    private ErrorCode errorCode;

    public ForbiddenException(ErrorCode errorCode) {
        super(errorCode);
    }
}
