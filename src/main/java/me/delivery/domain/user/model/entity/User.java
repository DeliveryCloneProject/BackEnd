package me.delivery.domain.user.model.entity;

import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.delivery.domain.user.model.vo.UserCreate;

import javax.persistence.*;

@Entity(name = "delivery_user")
@NoArgsConstructor
public class User extends BaseEntity {

    private String phone;

    private String nickname;

    private String password;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @Enumerated(EnumType.STRING)
    private UserType type;

    @Builder
    private User(
            String phone,
            String nickname,
            String password,
            String status,
            String type
    ){
        this.phone = phone;
        this.nickname = nickname;
        this.password = password;
        this.status = UserStatus.valueOf(status);
        this.type = UserType.valueOf(type);
    }

    public void setStatus(UserStatus status){
        this.status = status;
    }

}
