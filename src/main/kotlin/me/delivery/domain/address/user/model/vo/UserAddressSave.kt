package me.delivery.domain.address.user.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.delivery.domain.address.model.vo.AddressSaveParam;
import me.delivery.domain.address.user.model.entity.UserAddress;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
public class UserAddressSave {
    @Valid
    @NotNull(message = "주소르 입력해주세요.")
    private AddressSaveParam address;

    @Size(max = 255, message = "상세주소는 최대 255자 까지 입력 가능합니다.")
    private String detailAddress;

    public UserAddress toUserAddress(long addressId) {
        return UserAddress.builder()
                .addressId(addressId)
                .detailAddress(detailAddress)
                .build();
    }
}
