package me.delivery.domain.login.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.delivery.domain.login.model.dto.Login;
import me.delivery.domain.login.model.keys.LoginKeys;
import me.delivery.domain.login.service.ILoginService;
import me.delivery.domain.user.model.entity.User;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.concurrent.TimeUnit;

@Slf4j
@AllArgsConstructor
public class LoginService implements ILoginService {

    private final RedisTemplate<String,Object> redisTemplate;

    @Override
    public Login loginProcess(User user) {
        Login login = Login.builder()
                .user(user)
                .build();
        String key = generateKey(login.getId());
        ValueOperations<String, Object> vo = redisTemplate.opsForValue();
        vo.set(key,login);
        redisTemplate.expire(key, 5, TimeUnit.MINUTES); //5분간 session저장

        return login;
    }

    @Override
    public Login getUserSession(long userId) {
         String key = generateKey(userId);
         Login login = (Login) redisTemplate.opsForValue().get(key);
        return login;
    }


    private String generateKey(long userId){
        return LoginKeys.LOGIN_KEYS.getMessage() + "_" + userId;
    }
}
