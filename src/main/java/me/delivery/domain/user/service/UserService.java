package me.delivery.domain.user.service;

import me.delivery.domain.user.model.entity.User;

public interface UserService {
    User fineByNickname(String nickname);
}
