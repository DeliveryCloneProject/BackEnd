package me.delivery.domain.user.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author  leedy
 * @Description response 객체가 담길 Class. 특정 형태가 없이 boolean 이나 String으로 return 해야 할 때 사용.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ResponseObject<E> {

    private E result;

    public void set(E result){
        this.result = result;
    }


    public static <E> ResponseObject<E> of(E element) {
        return new ResponseObject(element);
    }
}
