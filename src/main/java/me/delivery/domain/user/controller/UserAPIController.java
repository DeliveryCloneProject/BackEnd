package me.delivery.domain.user.controller;

import lombok.RequiredArgsConstructor;
import me.delivery.domain.user.model.entity.User;
import me.delivery.domain.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.net.http.HttpResponse;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserAPIController {

    private final UserService userService;

    @GetMapping("/find/{nickname}")
    public User fineNickname (@PathVariable String nickname, HttpServletResponse res){
        User alreadyJoinedUser = userService.fineByNickname(nickname);
        return alreadyJoinedUser;
    }

}
