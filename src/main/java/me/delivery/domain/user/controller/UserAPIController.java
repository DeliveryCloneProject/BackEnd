package me.delivery.domain.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.delivery.common.exception.DeliveryExceotion;
import me.delivery.config.exception.InternalServerErrorException;
import me.delivery.domain.user.model.dto.ResponseObject;
import me.delivery.domain.user.model.entity.User;
import me.delivery.domain.user.model.entity.UserStatus;
import me.delivery.domain.user.model.vo.UserCreate;
import me.delivery.domain.user.model.vo.UserCreateParam;
import me.delivery.domain.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@RestControllerAdvice
@Slf4j
public class UserAPIController {
    private final UserService userService;


    /**
     * 가입하려는 고객의 닉네임 사용가능 여부 조회
     * @param nickname
     * @return boolean 
     */
    @GetMapping("/find/{nickname}")
    @ResponseBody
    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity fineNickname (@PathVariable String nickname, HttpServletResponse res){
        ResponseObject<Boolean> result = new ResponseObject<Boolean>();
        log.debug("nickname = "+nickname);
        User alreadyJoinedUser = userService.fineByNickname(nickname,UserStatus.Active);
        //기존 닉네임이 존재하지 않는다면 true. 사용가능함을 return
//        if(alreadyJoinedUser == null){
//        }else{
//            result.set(false);
//        }
        result.set(true);
        log.debug("result = "+result);
        return ResponseEntity.status(200).body(result);
    }

    @PostMapping("/join/createUser")
    public ResponseEntity createUser(@RequestBody @Valid UserCreateParam userInfo, HttpServletResponse res){
        ResponseObject<Boolean> result = new ResponseObject<Boolean>();

        String regExp = "^[0-9]*$";
        if(!Pattern.matches(regExp, userInfo.getPhone()) || "".equals(userInfo.getPhone()) )
            throw new DeliveryExceotion("휴대전화 번호가 올바르지 않습니다.");

        UserCreate param = userInfo.toUserCreate();
        param.setStatus(UserStatus.Active);
        userService.createUser(param);
        result.set(true);
        return ResponseEntity.status(200).body(result);
    }

    @PostMapping("/quit/{userId}")
    public ResponseEntity quitUser(@PathVariable long userId, HttpServletResponse res){
        ResponseObject<Boolean> result = new ResponseObject<Boolean>();
        try{
            userService.quitUser(userId);
            result.set(true);
        }catch(DeliveryExceotion e){
            result.set(false);
            res.setStatus(500);
        }
        return ResponseEntity.status(200).body(result);
    }

}
