package me.delivery.domain.login.service;

import me.delivery.domain.login.model.dto.Login;
import me.delivery.domain.user.model.entity.User;

public interface ILoginService {
    Login loginProcess(User user);

    Login findById(long id);

    void deleteSession(long id);
}
