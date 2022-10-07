package me.delivery.domain.address.user.model.error_code

import me.delivery.config.exception.ErrorCode

enum class UserAddressErrorCode (
    override val message: String,
) : ErrorCode {
    NotFound("주소르 찾을 수 없습니다."),
    DeleteForbidden("내가 등록한 주소만 삭제 가능합니다."),
    UpdateForbidden("내가 등록한 주소만 수정 가능합니다."),
    ;
}