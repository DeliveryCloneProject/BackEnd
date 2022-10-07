package me.delivery.domain.user.controller

import me.delivery.domain.user.model.vo.UserCreateParam
import me.delivery.domain.user.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/users")
class UserAPIController (
    private val userService: UserService,
) {

    @GetMapping("/find/{nickname}")
    fun findNickname(@PathVariable nickname: String) {
        userService.checkNicknameUsed(nickname)
    }

    @PostMapping("/join/create-user")
    @ResponseStatus(HttpStatus.CREATED)
    fun createUser(@RequestBody @Valid userInfo: UserCreateParam) {
        val user = userInfo.toEntity()
        userService.createUser(user)
    }

    @PostMapping("/quit/{userId}")
    fun quitUser(@PathVariable userId: Long) {
        userService.quitUser(userId)
    }
}