package me.delivery.domain.user.controller;

import lombok.RequiredArgsConstructor;
import me.delivery.common.exception.DeliveryExceotion;
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
import java.net.http.HttpResponse;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserAPIController {
    Logger logger = Logger.getLogger(UserAPIController.class.getName());
    private final UserService userService;

    /**
     * 가입하려는 고객의 닉네임 사용가능 여부 조회
     * @param nickname
     * @return boolean 
     */
    @GetMapping("/find/{nickname}")
    @ResponseBody
    public ResponseObject fineNickname (@PathVariable String nickname, HttpServletResponse res){
        ResponseObject result = new ResponseObject();
        System.out.println(UserStatus.Active.name());
        User alreadyJoinedUser = userService.fineByNickname(nickname,UserStatus.Active);
        logger.info("result = " + alreadyJoinedUser);
        //기존 닉네임이 존재하지 않는다면 true. 사용가능함을 return
        if(alreadyJoinedUser == null){
            result.setResult(true);
        }else{
            result.setResult(false);
        }
        return result;
    }

    @PostMapping("/join/createUser")
    public ResponseObject createUser(@RequestBody UserCreateParam userInfo, HttpServletResponse res){
        ResponseObject result = new ResponseObject();

        try{
            logger.info("start createUser. param = " + userInfo);

            String regExp = "^[0-9]*$";
            if(!Pattern.matches(regExp, userInfo.getPhone()))
                throw new DeliveryExceotion("휴대전화 번호가 올바르지 않습니다.");


            UserCreate param = userInfo.toUserCreate();
            param.setStatus(UserStatus.Active);
            userService.createUser(param);
            result.setResult(true);
        }catch(DeliveryExceotion e){
            logger.info(e.getMessage());
            res.setStatus(500);
            result.setResult(false);
        }
        return result;
    }

    @PostMapping("/quit/{userId}")
    public ResponseObject quitUser(@PathVariable long userId, HttpServletResponse res){
        ResponseObject result = new ResponseObject();
        try{
            userService.quitUser(userId);
            result.setResult(true);
        }catch(DeliveryExceotion e){
            logger.info(e.getMessage());
            result.setResult(false);
            res.setStatus(500);
        }
        return result;
    }

}
