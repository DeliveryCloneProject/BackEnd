package me.delivery.domain.address.model.vo

import me.delivery.domain.address.model.entity.Address
import org.springframework.data.geo.Point

class AddressVO (
    val id: Long,
    val address: String,
    val metropolitan: String,
    val city: String,
    val town: String,
    val road: String,
    val mainNumber: Int,
    val subNumber: Int,
    val point: Point,
){

    constructor(address: Address) : this(
        id = address.id,
        address = address.address,
        metropolitan = address.metropolitan,
        city = address.city,
        town = address.town,
        road = address.road,
        mainNumber = address.mainNumber,
        subNumber = address.subNumber,
        point = address.point
    )
}