package me.delivery.domain.user.model.vo;

import java.util.regex.Pattern;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.delivery.config.exception.BadRequestException;
import me.delivery.domain.user.model.entity.User;
import me.delivery.domain.user.model.error.UserErrorCode;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
/**
 * 유저에게 정보를 입력받는 클래스
 * 해당 클래스로 유저정보를 받고 서비스단에서 status를 결정 후 UserCreate로 변환
 */
public class UserCreateParam {
    @NotNull(message = "닉네임을 입력해주세요")
    private String nickname;
    @NotNull(message = "휴대폰 번호를 입력해주세요")
    private String phone;
    @NotNull(message = "비밀번호를 입력해주세요")
    private String password;
    @NotNull(message = "유저 타입이 필요합니다.")
    private String type;

    public User toEntity(){
        validate();
        return User.builder()
            .nickname(nickname)
            .password(password)
            .phone(phone)
            .type(type)
            .build();
    }

    private void validate() {
        String regExp = "^[0-9]*$";

        if (!Pattern.matches(regExp, phone)
            || phone.isBlank()
            || phone.length() < 11
        ) {
            throw new BadRequestException(UserErrorCode.PHONE_NOT_VALID);
        }
    }
}
