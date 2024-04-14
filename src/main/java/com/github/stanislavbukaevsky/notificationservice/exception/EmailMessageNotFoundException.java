package com.github.stanislavbukaevsky.notificationservice.exception;

/**
 * Класс-исключение для сообщения по электронной почте.
 * Наследуется от класса {@link RuntimeException}
 */
public class EmailMessageNotFoundException extends RuntimeException {
    public EmailMessageNotFoundException(String message) {
        super(message);
    }
}
