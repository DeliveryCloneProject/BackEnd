package me.delivery.domain.user.model.entity;

import javax.persistence.*;

@Entity(name = "delivery_user")
public class User extends BaseEntity {

    private String phone;

    private String nickname;

    private String password;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @Enumerated(EnumType.STRING)
    private UserType type;

}
