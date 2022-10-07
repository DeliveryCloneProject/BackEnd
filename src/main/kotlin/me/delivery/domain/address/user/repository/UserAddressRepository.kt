package me.delivery.domain.address.user.repository

import me.delivery.domain.address.user.model.entity.UserAddress
import org.springframework.data.jpa.repository.JpaRepository

interface UserAddressRepository : JpaRepository<UserAddress, Long> {
    fun findByUserIdAndDeletedAtIsNull(userId: Long): List<UserAddress>
}