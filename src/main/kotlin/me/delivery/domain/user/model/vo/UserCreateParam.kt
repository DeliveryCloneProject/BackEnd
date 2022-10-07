package me.delivery.domain.user.model.vo

import me.delivery.config.exception.BadRequestException
import me.delivery.domain.user.model.entity.User
import me.delivery.domain.user.model.entity.UserType
import me.delivery.domain.user.model.error.UserErrorCode
import java.util.regex.Pattern
import javax.validation.constraints.NotNull

class UserCreateParam(
    @NotNull(message = "닉네임을 입력해주세요")
    val nickname: String,
    @NotNull(message = "휴대폰 번호를 입력해주세요")
    val phone: String,
    @NotNull(message = "비밀번호를 입력해주세요")
    val password: String,
    @NotNull(message = "유저 타입이 필요합니다.")
    val type: UserType,
) {

    fun toEntity(): User {
        validate()
        return User(phone, nickname, password, type)
    }

    private fun validate() {
        val regExp = "^[0-9]{10,11}$"

        when {
            phone.isBlank() || !Pattern.matches(regExp, phone)
                -> throw BadRequestException(UserErrorCode.PHONE_NOT_VALID)
        }
    }
}