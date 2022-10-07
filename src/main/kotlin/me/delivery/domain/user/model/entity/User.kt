package me.delivery.domain.user.model.entity

import me.delivery.domain.entity.BaseEntity
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated

@Entity(name = "delivery_user")
class User (
    val phone: String,
    var nickname: String,
    var password: String,
    @Enumerated(EnumType.STRING)
    var status: UserStatus,
    @Enumerated(EnumType.STRING)
    var type: UserType
) : BaseEntity() {

    constructor(phone: String, nickname: String, password: String, type: UserType): this(
        phone = phone,
        nickname = nickname,
        password = password,
        status = UserStatus.Active,
        type = type
    )

    fun setStatusToQuit() {
        status = UserStatus.Quit
    }

    fun setStatusToActive() {
        status = UserStatus.Active
    }
}