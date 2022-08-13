package me.delivery.config.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends BaseException {
    private ErrorCode errorCode;

    public NotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
