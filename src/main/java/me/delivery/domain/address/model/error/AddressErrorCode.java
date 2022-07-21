package me.delivery.domain.address.model.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.delivery.config.exception.ErrorCode;

@Getter
@AllArgsConstructor
public enum AddressErrorCode implements ErrorCode {
    AddressNotFound("String")
    ;

    private String message;
}
