package me.delivery.domain.login.model.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Doyun
 * 로그인 정보를 담기 위한 Class
 */
@ToString
@Getter
@Setter
public class UserLoginParam {

    private String nickname;

    private String password;

}
