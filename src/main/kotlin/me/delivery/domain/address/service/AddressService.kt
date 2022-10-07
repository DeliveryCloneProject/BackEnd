package me.delivery.domain.address.service

import lombok.RequiredArgsConstructor
import me.delivery.domain.address.model.entity.Address
import me.delivery.domain.address.model.vo.AddressVO
import me.delivery.domain.address.repository.AddressRepository
import org.springframework.stereotype.Service
import java.util.*
import javax.transaction.Transactional

@Service
@RequiredArgsConstructor
class AddressService (
    private val addressRepository: AddressRepository
) {

    @Transactional
    fun save(address: Address): AddressVO {
        val find = findByAddress(address)
            ?: addressRepository.save(address)

        return find.toImmutable()
    }

    private fun findByAddress(address: Address): Address? {
        return addressRepository.findByAddress(address.address)
    }
}