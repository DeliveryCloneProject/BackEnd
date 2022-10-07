package me.delivery.domain.user.model.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.Builder;
import lombok.NoArgsConstructor;

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
            String type
    ){
        this.phone = phone;
        this.nickname = nickname;
        this.password = password;
        this.type = UserType.valueOf(type);
    }


    /**
     * @Description 탈퇴 유저로 설정
     */
    public void setStatusToQuit(){
        this.status = UserStatus.Quit;
    }

    /**
     * @Description 활동 유저로 설정
     */
    public void setStatusToActive(){
        this.status = UserStatus.Active;
    }

}
