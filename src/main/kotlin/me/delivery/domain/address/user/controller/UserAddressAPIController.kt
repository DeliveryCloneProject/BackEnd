package me.delivery.domain.address.user.controller

import me.delivery.domain.address.user.model.vo.UserAddressSave
import me.delivery.domain.address.user.model.vo.UserAddressUpdate
import me.delivery.domain.address.user.model.vo.UserAddressVO
import me.delivery.domain.address.user.service.UserAddressService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/address/users")
class UserAddressAPIController (
    private val userAddressService: UserAddressService,
) {

    @GetMapping("/me")
    fun myAddress(): List<UserAddressVO> {
        val userId = 0L
        return userAddressService.myAddresses(userId)
    }

    @PostMapping
    fun save(@RequestBody @Valid param: UserAddressSave): UserAddressVO {
        val userId = 0L
        return userAddressService.save(param, userId)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) {
        val userId = 0L
        userAddressService.delete(id, userId)
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun updateMemo(@PathVariable id: Long, @RequestBody @Valid param: UserAddressUpdate) {
        val userId = 0
        userAddressService.updateMemo(id, userId, param.memo, param.detailAddress)
    }
}