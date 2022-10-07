package me.delivery.domain.user.repository

import me.delivery.domain.user.model.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
    fun findByNickname(nickname: String): User?
}