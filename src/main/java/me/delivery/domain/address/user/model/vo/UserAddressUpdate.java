package me.delivery.domain.address.user.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
public class UserAddressUpdate {
    @Size(max = 50, message = "메모는 최대 50글자 까지 입력가능합니다.")
    private String memo;
    @Size(max = 100, message = "상세주소는 최대 100글자까지 입력가능합니다.")
    private String detailAddress;
}
