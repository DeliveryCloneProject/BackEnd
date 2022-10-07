package me.delivery.domain.address.model.entity

import me.delivery.domain.address.model.vo.AddressVO
import me.delivery.domain.entity.BaseEntity
import org.springframework.data.geo.Point
import java.math.BigDecimal
import javax.persistence.*
import kotlin.jvm.Transient

@Entity(name = "address")
class Address (
    val address: String,
    val metropolitan: String,
    val city: String,
    val town: String,
    val road: String,
    val mainNumber: Int,
    val subNumber: Int,
    @Transient
    val x: BigDecimal,
    @Transient
    val y: BigDecimal,
): BaseEntity() {

    @Column(name = "point", columnDefinition = "POINT")
    val point: Point = Point(x.toDouble(), y.toDouble())

    fun toImmutable(): AddressVO = AddressVO(this)
}