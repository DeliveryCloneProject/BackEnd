package me.delivery.domain.address.user.service

import me.delivery.config.exception.NotFoundException
import me.delivery.domain.address.service.AddressService
import me.delivery.domain.address.user.model.error_code.UserAddressErrorCode
import me.delivery.domain.address.user.model.vo.UserAddressSave
import me.delivery.domain.address.user.model.vo.UserAddressVO
import me.delivery.domain.address.user.repository.UserAddressRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class UserAddressService (
    private val userAddressRepository: UserAddressRepository,
    private val addressService: AddressService,
) {

    fun myAddresses(userId: Long): List<UserAddressVO> {
        return userAddressRepository.findByUserIdAndDeletedAtIsNull(userId)
            .map { UserAddressVO(it) }
    }

    @Transactional
    fun save(param: UserAddressSave, userId: Long): UserAddressVO {
        val address = param.address.toEntity()

        val addressVO = addressService.save(address)
        val userAddress = param.toUserAddress(address.id, userId)

        userAddressRepository.save(userAddress)

        return userAddress.toImmutable(addressVO)
    }

    @Transactional
    fun delete(id: Long, userId: Long) {
        val userAddress = userAddressRepository.findByIdOrNull(id)
            ?: throw NotFoundException(UserAddressErrorCode.NotFound)

        userAddress.delete(userId)
    }

    @Transactional
    fun updateMemo(id: Long, userId: Int, memo: String, detailAddress: String) {
        val userAddress = userAddressRepository.findByIdOrNull(id)
            ?: throw NotFoundException(UserAddressErrorCode.NotFound)

        userAddress.updateMemoAndDetailAddress(userId, memo, detailAddress)
    }
}