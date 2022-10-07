package me.delivery.config.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.FORBIDDEN)
class ForbiddenException(errorCode: ErrorCode) : BaseException(errorCode)