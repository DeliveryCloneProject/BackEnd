package me.delivery.domain.user.service.impl;

import lombok.RequiredArgsConstructor;
import me.delivery.common.exception.DeliveryExceotion;
import me.delivery.domain.user.model.entity.User;
import me.delivery.domain.user.repository.UserRepository;
import me.delivery.domain.user.service.UserService;
import org.springframework.stereotype.Service;

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
    public User fineByNickname(String nickname) {
        User user = userRepository.findByNickname(nickname);
        return user;
    }

    /**
     * Description 사용자 등록
     * @param userInfo
     */
    @Override
    public void createUser(User userInfo) {
        try{
            userRepository.saveAndFlush(userInfo);
        }catch(Exception e){
            throw new DeliveryExceotion("유저 등록 중 오류");
        }
    }
}
