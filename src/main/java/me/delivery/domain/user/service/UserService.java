package me.delivery.domain.user.service;


import me.delivery.domain.user.model.entity.User;
import me.delivery.domain.user.model.entity.UserStatus;
import me.delivery.domain.user.model.vo.UserCreate;
import me.delivery.domain.user.model.vo.UserCreateParam;

public interface UserService {
    User fineByNickname(String nickname, UserStatus status);

    void createUser(UserCreate userInfo);

    void quitUser(long userId);
}
