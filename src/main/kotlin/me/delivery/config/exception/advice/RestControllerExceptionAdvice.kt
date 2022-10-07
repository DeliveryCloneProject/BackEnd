package me.delivery.config.exception.advice

import me.delivery.config.exception.BaseException
import me.delivery.config.exception.InternalServerErrorException
import mu.KotlinLogging
import org.springframework.core.annotation.AnnotationUtils
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.util.stream.Collectors
import javax.validation.ConstraintViolation
import javax.validation.ConstraintViolationException

@RestControllerAdvice
class RestControllerExceptionAdvice {
    private val log = KotlinLogging.logger { }

    @ExceptionHandler(BaseException::class)
    fun baseExceptionHandler(ex: BaseException): ResponseEntity<ErrorResponse> {
        val annotation = AnnotationUtils.findAnnotation(
            ex.javaClass,
            ResponseStatus::class.java
        )
        val status: HttpStatus = annotation!!.code
        val message = ex.message ?: ""

        if (ex is InternalServerErrorException) {
            log.error(ex.message, ex)
        }

        return generateResponse(status, message)
    }

    @ExceptionHandler(ConstraintViolationException::class)
    fun validationException(ex: ConstraintViolationException): ResponseEntity<ErrorResponse> {
        val status = HttpStatus.BAD_REQUEST
        val message = ex.constraintViolations
            .stream()
            .map { exception: ConstraintViolation<*> -> exception.message }
            .collect(Collectors.joining("\n"))
        return generateResponse(status, message)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun notValidException(ex: MethodArgumentNotValidException): ResponseEntity<ErrorResponse> {
        val status = HttpStatus.BAD_REQUEST
        val message = ex.bindingResult
            .fieldErrors
            .stream().map { obj: FieldError -> obj.defaultMessage }
            .collect(Collectors.joining("\n"))
        return generateResponse(status, message)
    }

    @ExceptionHandler(Exception::class)
    fun exception(ex: Exception): ResponseEntity<ErrorResponse> {
        log.error(ex.message, ex)
        val status = HttpStatus.INTERNAL_SERVER_ERROR
        val message = "알 수 없는 오류가 발생했습니다."
        return generateResponse(status, message)
    }

    private fun generateResponse(status: HttpStatus, message: String): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(message)
       return ResponseEntity(errorResponse, status)
    }

}