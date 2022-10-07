package me.delivery.domain.address.repository

import me.delivery.domain.address.model.entity.Address
import org.springframework.data.jpa.repository.JpaRepository

interface AddressRepository : JpaRepository<Address, Long> {
    fun findByAddress(address: String): Address?
}