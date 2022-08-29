package me.delivery.domain.login.model.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.delivery.config.exception.ErrorCode;

@Getter
@RequiredArgsConstructor
public enum LoginError implements ErrorCode {
    SESSION_NOT_FOUNT("로그인이 필요한 기능입니다.");

    private final String message;
}
