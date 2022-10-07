package me.delivery.domain.address.user.model.entity

import me.delivery.config.exception.ForbiddenException
import me.delivery.domain.address.model.entity.Address
import me.delivery.domain.address.model.vo.AddressVO
import me.delivery.domain.address.user.model.error_code.UserAddressErrorCode
import me.delivery.domain.address.user.model.vo.UserAddressVO
import me.delivery.domain.entity.BaseEntity
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
class UserAddress(
    val addressId: Long,
    val userId: Long,
    var detailAddress: String,
    var memo: String? = null,
    @LastModifiedDate
    var updatedAt: LocalDateTime? = null,
    var deletedAt: LocalDateTime? = null,
): BaseEntity() {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "addressId", insertable = false, updatable = false)
    lateinit var address: Address

    fun toImmutable(addressVO: AddressVO) = UserAddressVO(this)

    fun delete(userId: Long) {
        if (this.userId != userId) {
            throw ForbiddenException(UserAddressErrorCode.DeleteForbidden)
        }
        deletedAt = LocalDateTime.now()
    }

    fun updateMemoAndDetailAddress(userId: Int, memo: String, detailAddress: String) {
        if (this.userId != userId.toLong()) {
            throw ForbiddenException(UserAddressErrorCode.UpdateForbidden)
        }
        this.memo = memo
        this.detailAddress = detailAddress
    }
}