package me.delivery.domain.user.controller;

import javax.validation.Valid;

import me.delivery.config.exception.BadRequestException;
import me.delivery.config.exception.BaseException;
import me.delivery.config.exception.InternalServerErrorException;
import me.delivery.domain.user.model.dto.ResponseObject;
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
    public ResponseObject findNickname(@PathVariable String nickname) {
        log.debug("find user nickname => "+nickname);
        userService.checkNicknameUsed(nickname);
        ResponseObject<Boolean> result = new ResponseObject<>();
        result.set(true);
        return result;
    }

    @PostMapping("/join/create-user")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseObject createUser(@RequestBody @Valid UserCreateParam userInfo) {
        User user = userInfo.toEntity();
        userService.createUser(user);
        ResponseObject<Boolean> result = new ResponseObject<>();
        result.set(true);
        return result;
    }

    @PostMapping("/quit/{userId}")
    public ResponseObject quitUser(@PathVariable long userId) {
        userService.quitUser(userId);
        ResponseObject<Boolean> result = new ResponseObject<>();
        result.set(true);
        return result;
    }

}
