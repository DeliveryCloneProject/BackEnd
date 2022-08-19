package me.delivery.domain.login.model.keys;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum LoginKeys {
    LOGIN_KEYS("member");

    private final String message;

}
