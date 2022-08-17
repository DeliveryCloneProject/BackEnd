package me.delivery.domain.user.service.impl;

import lombok.RequiredArgsConstructor;
import me.delivery.common.exception.DeliveryExceotion;
import me.delivery.domain.user.model.entity.User;
import me.delivery.domain.user.model.entity.UserStatus;
import me.delivery.domain.user.model.entity.UserType;
import me.delivery.domain.user.model.vo.UserCreate;
import me.delivery.domain.user.repository.UserRepository;
import me.delivery.domain.user.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IUserService implements UserService {
    private final UserRepository userRepository;

    /**
     * @Description 닉네임으로 해당 유저가 존재하는지 탐색한다.
     * @param nickname
     * @return user
     */
    @Override
    public User fineByNickname(String nickname, UserStatus status) {
        User user = userRepository.findByNicknameAndStatus(nickname, status);
        return user;
    }

    /**
     * Description 사용자 등록
     * @param userInfo
     */
    @Override
    public void createUser(UserCreate userInfo) {
        try{
            userRepository.saveAndFlush(userInfo.toEntity());
        }catch(Exception e){
            throw new DeliveryExceotion("유저 등록 중 오류");
        }
    }

    /**
     * 탈퇴 처리
     * @param userId
     */
    @Override
    public void quitUser(long userId) {
        try{
            Optional<User> user = userRepository.findById(userId);
            User param = user.get();
            param.setStatus(UserStatus.Quit);

            userRepository.save(param);
        }catch(Exception e){
            throw new DeliveryExceotion("탈퇴 처리 중 오류 발생");
        }
    }
}
