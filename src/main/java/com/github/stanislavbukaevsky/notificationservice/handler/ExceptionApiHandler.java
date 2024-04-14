package com.github.stanislavbukaevsky.notificationservice.handler;

import com.github.stanislavbukaevsky.notificationservice.exception.EmailMessageNotFoundException;
import com.github.stanislavbukaevsky.notificationservice.util.ResponseApiException;
import com.github.stanislavbukaevsky.notificationservice.util.ResponseValidationException;
import com.github.stanislavbukaevsky.notificationservice.util.Violation;
import com.google.gson.JsonParseException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Этот класс для обработки всех исключений приложения
 */
@RestControllerAdvice
@Slf4j
public class ExceptionApiHandler {

    /**
     * Этот метод обрабатывает все исключения, возникшие с парсингом из JSON
     *
     * @param exception исключение
     * @return Возвращает сформированное сообщение пользователю об ошибке, возникшей в результате неправильного запроса
     */
    @ExceptionHandler(JsonParseException.class)
    public ResponseEntity<ResponseApiException> jsonParseException(JsonParseException exception) {
        log.error(exception.getMessage(), exception);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ResponseApiException(HttpStatus.NOT_FOUND.value(), exception.getMessage(), getDateTime()));
    }

    /**
     * Этот метод обрабатывает все исключения, возникшие с отправкой уведомления на электронную почту
     *
     * @param exception исключение
     * @return Возвращает сформированное сообщение пользователю об ошибке, возникшей в результате неправильного запроса
     */
    @ExceptionHandler(EmailMessageNotFoundException.class)
    public ResponseEntity<ResponseApiException> emailMessageNotFoundException(EmailMessageNotFoundException exception) {
        log.error(exception.getMessage(), exception);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ResponseApiException(HttpStatus.BAD_REQUEST.value(), exception.getMessage(), getDateTime()));
    }

    /**
     * Этот метод обрабатывает все исключения, возникшие с неправильной валидацией
     *
     * @param exception исключение
     * @return Возвращает сформированное сообщение пользователю об ошибке, возникшей в результате неправильного запроса
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ResponseValidationException> onConstraintValidationException(ConstraintViolationException exception) {
        final List<Violation> violations = exception.getConstraintViolations().stream()
                .map(violation ->
                        new Violation(violation.getPropertyPath().toString(), violation.getMessage(), getDateTime())
                ).collect(Collectors.toList());
        log.error(exception.getMessage(), exception);
        return ResponseEntity.ok(new ResponseValidationException(violations));
    }

    /**
     * Этот метод обрабатывает все исключения, возникшие с неправильной валидацией
     *
     * @param exception исключение
     * @return Возвращает сформированное сообщение пользователю об ошибке, возникшей в результате неправильного запроса
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseValidationException> onMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        final List<Violation> violations = exception.getBindingResult().getFieldErrors().stream()
                .map(violation ->
                        new Violation(violation.getField(), violation.getDefaultMessage(), getDateTime())
                ).collect(Collectors.toList());
        log.error(exception.getMessage(), exception);
        return ResponseEntity.ok(new ResponseValidationException(violations));
    }

    private LocalDateTime getDateTime() {
        return LocalDateTime.now();
    }
}
