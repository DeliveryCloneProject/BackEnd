package me.delivery.domain.address.user.model.vo

import javax.validation.constraints.Size

class UserAddressUpdate (
    @Size(max = 50, message = "메모는 최대 50글자 까지 입력가능합니다.")
    val memo: String,
    @Size(max = 100, message = "상세주소는 최대 100글자까지 입력가능합니다.")
    val detailAddress: String,
)