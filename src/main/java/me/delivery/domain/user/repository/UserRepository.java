package me.delivery.domain.user.repository;

import me.delivery.domain.login.model.vo.UserLoginParam;
import me.delivery.domain.user.model.entity.User;
import me.delivery.domain.user.model.entity.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByNickname(String nickname);

    User findByNicknameAndPassword(String nickname, String password);
}
