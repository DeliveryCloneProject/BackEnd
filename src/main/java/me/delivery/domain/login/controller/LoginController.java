package me.delivery.domain.login.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.delivery.domain.login.model.dto.Login;
import me.delivery.domain.login.model.keys.LoginKeys;
import me.delivery.domain.login.model.vo.UserLoginParam;
import me.delivery.domain.login.service.ILoginService;
import me.delivery.domain.user.model.dto.ResponseObject;
import me.delivery.domain.user.model.entity.User;
import me.delivery.domain.user.service.IUserService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.concurrent.TimeUnit;

/**
 * @Description Login 처리를 담당
 * @author  Doyun
 */
@RestController
@RequestMapping("/api/login")
@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    private final IUserService userService;

    private final ILoginService loginService;

    @PostMapping("/login-user")
    //@Cacheable(key = "#param.nickname", cacheNames = "member")   //캐싱은 좀 더 학습 필요
    public ResponseObject doLogin(@RequestBody @Valid UserLoginParam param){
        User user = userService.findByNicknameAndPassword(param);
        Login result = loginService.loginProcess(user);

        ResponseObject<Login> res = new ResponseObject<Login>();
        res.set(result);

        return res;
    }

    /*테스트*/
    @GetMapping("/getUser/{id}")
    public ResponseObject getUser(@PathVariable long id){
        Login rSess = loginService.getUserSession(id);
        ResponseObject<Login> res = new ResponseObject<Login>();
        res.set(rSess);
        return res;
    }



}
