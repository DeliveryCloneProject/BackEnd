package me.delivery.domain.user.service;

import me.delivery.domain.user.model.entity.User;

public interface IUserService {
    void checkNicknameUsed(String nickname);

    void createUser(User userInfo);

    void quitUser(long userId);
}
