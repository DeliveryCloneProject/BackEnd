package me.delivery.domain.login.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import me.delivery.domain.user.model.entity.User;

import java.io.Serializable;

@NoArgsConstructor
@ToString
@Getter
public class Login implements Serializable {
    private static final long serialId = 1L;
    private long id;

    private String nickname;



   @Builder
    private Login(
       User user
   ){
       this.id = user.getId();
       this.nickname = user.getNickname();
   }
}
