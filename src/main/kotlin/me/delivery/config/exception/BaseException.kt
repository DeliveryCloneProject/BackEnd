package me.delivery.config.exception

open class BaseException(
    private val errorCode: ErrorCode
) : RuntimeException(errorCode.message)