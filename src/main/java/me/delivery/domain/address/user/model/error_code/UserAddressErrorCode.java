package me.delivery.domain.address.user.model.error_code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.delivery.config.exception.ErrorCode;

@Getter
@AllArgsConstructor
public enum UserAddressErrorCode implements ErrorCode {
    NotFound("주소르 찾을 수 없습니다."),
    DeleteForbidden("내가 등록한 주소만 삭제 가능합니다."),
    UpdateForbidden("내가 등록한 주소만 수정 가능합니다.");

    private String message;
}
