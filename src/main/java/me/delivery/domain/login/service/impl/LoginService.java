package me.delivery.domain.login.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.delivery.config.exception.NotFoundException;
import me.delivery.domain.login.model.dto.Login;
import me.delivery.domain.login.model.error.LoginError;
import me.delivery.domain.login.model.keys.LoginKeys;
import me.delivery.domain.login.repository.LoginRedisRepository;
import me.delivery.domain.login.service.ILoginService;
import me.delivery.domain.user.model.entity.User;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j
@AllArgsConstructor
@Service
public class LoginService implements ILoginService {

    private final RedisTemplate<String,Object> redisTemplate;

    private final LoginRedisRepository loginRedidsRepo;

    @Override
    public Login loginProcess(User user) {
        Login login = Login.builder()
                .user(user)
                .build();
        /* session 객체에 넣는것이 더 좋을것 같아서 봉인함.
        loginRedidsRepo.save(login);
        log.debug("insert user session success"+login);
        */
        return login;
    }


    @Override
    public Login findById(long id) {
        Login result = null;
        try{
            result = loginRedidsRepo.findById(id).get();
        }catch(Exception e){
            new NotFoundException(LoginError.SESSION_NOT_FOUNT);
        }
        return result;
    }

    @Override
    public void deleteSession(long id) {
        try{
            loginRedidsRepo.deleteById(id);
        }catch(Exception e){
            new NotFoundException(LoginError.SESSION_NOT_FOUNT);
        }
    }


    private String generateKey(long userId){
        return LoginKeys.LOGIN_KEYS.getMessage() + "_" + userId;
    }
}
