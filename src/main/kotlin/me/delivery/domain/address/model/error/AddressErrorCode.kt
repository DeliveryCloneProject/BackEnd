package me.delivery.domain.address.model.error

import lombok.AllArgsConstructor
import lombok.Getter
import me.delivery.config.exception.ErrorCode

@Getter
@AllArgsConstructor
enum class AddressErrorCode(
    override val message: String
) : ErrorCode {
    AddressNotFound("String");
}