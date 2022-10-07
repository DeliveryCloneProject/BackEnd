package me.delivery.domain.entity

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.io.Serializable
import java.time.LocalDateTime
import javax.persistence.*

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
class BaseEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT UNSIGNED", nullable = false)
    open var id: Long = 0,

    @CreatedDate
    @Column(columnDefinition = "DATETIME", nullable = false)
    open var createdAt: LocalDateTime = LocalDateTime.now()
): Serializable