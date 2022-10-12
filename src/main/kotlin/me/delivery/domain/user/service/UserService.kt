package me.delivery.domain.user.service

import me.delivery.config.exception.BadRequestException
import me.delivery.config.exception.NotFoundException
import me.delivery.domain.user.model.entity.User
import me.delivery.domain.user.model.error.UserErrorCode
import me.delivery.domain.user.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService (
    private val userRepository: UserRepository,
) {

    fun checkNicknameUsed(nickname: String) {
        userRepository.findByNickname(nickname)?.run {
            throw BadRequestException(UserErrorCode.ALREADY_USE_NICKNAME)
        }
    }


    @Transactional
    fun createUser(user: User) {
        user.setStatusToActive()
        userRepository.save(user)
    }

    @Transactional
    fun quitUser(userId: Long) {
        val user = userRepository.findByIdOrNull(userId)
            ?: throw NotFoundException(UserErrorCode.USER_NOT_FOUND)
        user.setStatusToQuit()
    }
}