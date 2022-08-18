package me.delivery.domain.user.model.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.delivery.config.exception.ErrorCode;

@Getter
@RequiredArgsConstructor
public enum UserErrorCode implements ErrorCode {
	USER_NOT_FOUND("사용자를 찾을 수 없습니다."),
	ALREADY_USE_NICKNAME("이미 사용중인 닉네임 입니다."),
	PHONE_NOT_VALID("휴대폰 번호가 올바르지 않습니다.");

	private final String message;
}
