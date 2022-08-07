package me.delivery.domain.user.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author  leedy
 * @Description response 객체가 담길 Class. 특정 형태가 없이 boolean 이나 String으로 return 해야 할 때 사용.
 */
@Getter
@Setter
@NoArgsConstructor
public class ResponseObject {

    private Object result;
}
