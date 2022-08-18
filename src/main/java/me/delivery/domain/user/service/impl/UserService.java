package me.delivery.domain.user.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.delivery.config.exception.BadRequestException;
import me.delivery.config.exception.NotFoundException;
import me.delivery.domain.user.model.entity.User;
import me.delivery.domain.user.model.error.UserErrorCode;
import me.delivery.domain.user.repository.UserRepository;
import me.delivery.domain.user.service.IUserService;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements IUserService {
    private final UserRepository userRepository;

    /**
     * @Description 닉네임으로 해당 유저가 존재하는지 탐색한다.
     */
    public void checkNicknameUsed(String nickname) {
        User user = userRepository.findByNickname(nickname);

        if (user != null) {
            throw new BadRequestException(UserErrorCode.ALREADY_USE_NICKNAME);
        }
    }

    /**
     * Description 사용자 등록
     */
    @Transactional
    public void createUser(User user) {
        user.setStatusToActive();

        userRepository.save(user);
    }

    /**
     * 탈퇴 처리
     */
    @Transactional
    public void quitUser(long userId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new NotFoundException(UserErrorCode.USER_NOT_FOUND));
        user.setStatusToQuit();
    }
}
