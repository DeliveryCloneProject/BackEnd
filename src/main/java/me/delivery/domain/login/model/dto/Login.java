package me.delivery.domain.login.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import me.delivery.domain.user.model.entity.User;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@ToString
@RedisHash("member")
public class Login implements Serializable {
    private static final long serialId = 1L;
    @Id
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
