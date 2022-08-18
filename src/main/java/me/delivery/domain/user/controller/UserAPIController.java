package me.delivery.domain.user.controller;

import javax.validation.Valid;

import me.delivery.config.exception.BadRequestException;
import me.delivery.config.exception.BaseException;
import me.delivery.config.exception.InternalServerErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.delivery.domain.user.model.entity.User;
import me.delivery.domain.user.model.vo.UserCreateParam;
import me.delivery.domain.user.service.IUserService;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Slf4j
@RestControllerAdvice
public class UserAPIController {
    private final IUserService userService;


    /**
     * 가입하려는 고객의 닉네임 사용가능 여부 조회
     */
    @GetMapping("/find/{nickname}")
    public void findNickname(@PathVariable String nickname) {
        userService.checkNicknameUsed(nickname);
    }

    @PostMapping("/join/create-user")
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody @Valid UserCreateParam userInfo) {
        User user = userInfo.toEntity();
        userService.createUser(user);
    }

    @PostMapping("/quit/{userId}")
    public void quitUser(@PathVariable long userId) {
        userService.quitUser(userId);
    }

}
