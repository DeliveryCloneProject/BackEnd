package me.delivery.config.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
class InternalServerErrorException(errorCode: ErrorCode) : BaseException(errorCode)