package me.delivery.domain.user.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

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

    public UserCreate toUserCreate(){
        UserCreate vo = new UserCreate();
        vo.setNickname(this.nickname);
        vo.setPassword(this.password);
        vo.setPhone(this.phone);
        vo.setType(this.type);
        return vo;
    }
    @Override
    public String toString(){
        return "nickname = "+nickname+" / phone = "+phone;
    }
}
