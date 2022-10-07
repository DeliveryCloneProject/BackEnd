package me.delivery.domain.address.user.model.vo

import me.delivery.domain.address.model.vo.AddressVO
import me.delivery.domain.address.user.model.entity.UserAddress
import java.time.LocalDateTime

class UserAddressVO (
    val id: Long,
    val detailAddress: String,
    val memo: String?,
    val address: AddressVO,
    val createdAt: LocalDateTime,
) {
    constructor(userAddress: UserAddress): this(
        id = userAddress.id,
        detailAddress = userAddress.detailAddress,
        memo = userAddress.memo,
        address = AddressVO(userAddress.address),
        createdAt = userAddress.createdAt,
    )
}