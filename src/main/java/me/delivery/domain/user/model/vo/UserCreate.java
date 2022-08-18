package me.delivery.domain.user.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.delivery.domain.user.model.entity.User;
import me.delivery.domain.user.model.entity.UserStatus;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
public class UserCreate {

    private String nickname;
    private String phone;
    private String password;
    private String type;
    private UserStatus status;

    public User toEntity(){
        return User.builder()
                .nickname(nickname)
                .password(password)
                .phone(phone)
                .type(type)
                .build();
    }


}
