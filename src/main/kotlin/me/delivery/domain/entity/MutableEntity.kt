package me.delivery.domain.entity

import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass


@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
class MutableEntity (
    @LastModifiedDate
    @Column(columnDefinition = "DATETIME", nullable = false)
    open var updatedAt: LocalDateTime = LocalDateTime.now()
): BaseEntity()