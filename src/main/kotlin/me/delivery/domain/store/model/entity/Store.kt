package me.delivery.domain.store.model.entity

import me.delivery.domain.entity.BaseEntity
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated

@Entity
class Store (
    val name: String,
    @Enumerated(EnumType.STRING)
    val status: StoreStatus,
): BaseEntity() {

}