package me.delivery.domain.user.controller;

import lombok.RequiredArgsConstructor;
import me.delivery.domain.user.model.entity.User;
import me.delivery.domain.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserAPIController {

    private final UserService userService;

    @GetMapping("/find/{nickname}")
    @ResponseStatus(value= HttpStatus.NO_CONTENT)
    public void fineNickname (@PathVariable String nickname){
        User alreadyJoinedUser = userService.fineByNickname(nickname);
    }

}
