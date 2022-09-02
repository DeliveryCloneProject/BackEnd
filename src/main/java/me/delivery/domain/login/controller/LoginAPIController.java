package me.delivery.domain.login.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.delivery.config.exception.NotFoundException;
import me.delivery.domain.login.model.dto.Login;
import me.delivery.domain.login.model.error.LoginError;
import me.delivery.domain.login.model.keys.LoginKeys;
import me.delivery.domain.login.model.vo.UserLoginParam;
import me.delivery.domain.login.service.ILoginService;
import me.delivery.domain.user.model.dto.ResponseObject;
import me.delivery.domain.user.model.entity.User;
import me.delivery.domain.user.service.IUserService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * @author Doyun
 * @Description Login 처리를 담당
 */
@RestController
@RequestMapping("/api/login")
@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class LoginAPIController {

    private final IUserService userService;

    private final ILoginService loginService;

    @PostMapping("/login-user")
    //@Cacheable(key = "#param.nickname", cacheNames = "member")   //Cache
    public ResponseObject doLogin(@RequestBody @Valid UserLoginParam param, HttpSession session) {
        User user = userService.findByNicknameAndPassword(param);
        Login login = loginService.loginProcess(user);

        String key = generateKey(login.getId());

        //repository사용여부는 상의 후 처리
        session.setAttribute(key, login);

        ResponseObject<Login> res = new ResponseObject<Login>();
        res.set(login);
        return res;
    }

    /*테스트*/
    @GetMapping("/getUser/{id}")
    public ResponseObject getUser(@PathVariable long id, HttpSession session) {
        //Login rSess = loginService.findById(id);
        String key = generateKey(id);
        Login rSess = (Login) session.getAttribute(key);
        if (rSess == null) {
            throw new NotFoundException(LoginError.SESSION_NOT_FOUNT);
        }

        ResponseObject<Login> res = new ResponseObject<Login>();
        res.set(rSess);
        return res;
    }

    private String generateKey(long userId) {
        return LoginKeys.LOGIN_KEYS.getMessage() + userId;
    }

    @GetMapping("/logout-user/{id}")
    public ResponseObject logoutUser(@PathVariable long id, HttpSession session) {
        String key = generateKey(id);
        //Login rSess = loginService.findById(id);
        //loginService.deleteSession(id);

        Login rSess =(Login) session.getAttribute(key);
        if (rSess == null) {
            throw new NotFoundException(LoginError.SESSION_NOT_FOUNT);
        }
        session.removeAttribute(key);
        log.debug("delete user session success");
        ResponseObject<Boolean> res = new ResponseObject<Boolean>();
        res.set(true);
        return res;
    }

}
