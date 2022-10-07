package me.delivery.domain.address.user.model.vo

import me.delivery.domain.address.model.vo.AddressSaveParam
import me.delivery.domain.address.user.model.entity.UserAddress
import javax.validation.Valid
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

class UserAddressSave (
    @Valid
    @NotNull(message = "주소르 입력해주세요.")
    val address:  AddressSaveParam,
    @Size(max = 255, message = "상세주소는 최대 255자 까지 입력 가능합니다.")
    val detailAddress:  String,
) {

    fun toUserAddress(addressId: Long, userId: Long): UserAddress {
        return UserAddress(
            addressId = addressId,
            detailAddress =  detailAddress,
            userId = userId
        )
    }
}