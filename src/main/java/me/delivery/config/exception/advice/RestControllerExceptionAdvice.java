package me.delivery.config.exception.advice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.delivery.common.exception.DeliveryExceotion;
import me.delivery.config.exception.BaseException;
import me.delivery.config.exception.InternalServerErrorException;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class RestControllerExceptionAdvice {

    private final ObjectMapper objectMapper;

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ErrorResponse> baseExceptionHandler(BaseException ex) {
        ResponseStatus annotation = AnnotationUtils.findAnnotation(ex.getClass(), ResponseStatus.class);

        HttpStatus status = annotation.code();
        String message = ex.getMessage();

        if (ex instanceof InternalServerErrorException) {
            log.error(ex.getMessage(), ex);
        }

        return generateResponse(status, message);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> validationException(ConstraintViolationException ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String message = ex.getConstraintViolations()
                .stream()
                .map(exception -> exception.getMessage())
                .collect(Collectors.joining("\n"));

        return generateResponse(status, message);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> notValidException(MethodArgumentNotValidException ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String message = ex.getBindingResult()
                .getFieldErrors()
                .stream().map(FieldError::getDefaultMessage)
                .collect(Collectors.joining("\n"));

        return generateResponse(status, message);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> exception(Exception ex) {
        log.error(ex.getMessage(), ex);

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        String message = "알 수 없는 오류가 발생했습니다.";

        return generateResponse(status, message);
    }


    private ResponseEntity<ErrorResponse> generateResponse(HttpStatus status, String message) {
        try {
            ErrorResponse errorResponse = ErrorResponse.builder()
                    .message(message)
                    .build();
            String json = objectMapper.writeValueAsString(errorResponse);
            return new ResponseEntity(json, status);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @ExceptionHandler(DeliveryExceotion.class)
    private ResponseEntity<ErrorResponse> exception(DeliveryExceotion e){
        log.debug("error===============");
        log.error(e.getMessage(), e);
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        return generateResponse(status, e.getMessage());
    }
}
