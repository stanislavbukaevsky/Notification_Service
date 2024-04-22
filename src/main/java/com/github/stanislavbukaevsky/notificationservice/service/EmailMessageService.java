package com.github.stanislavbukaevsky.notificationservice.service;

import com.github.stanislavbukaevsky.notificationservice.dto.EmailMessageRequestDto;
import com.github.stanislavbukaevsky.notificationservice.dto.EmailMessageResponseDto;
import com.github.stanislavbukaevsky.notificationservice.exception.EmailMessageNotFoundException;
import com.github.stanislavbukaevsky.notificationservice.model.Comment;
import com.github.stanislavbukaevsky.notificationservice.model.Task;
import jakarta.validation.Valid;

import static com.github.stanislavbukaevsky.notificationservice.constant.ExceptionTextMessageConstant.EMAIL_MESSAGE_NOT_FOUND_EXCEPTION_MESSAGE;

/**
 * Сервис-интерфейс для отправки уведомления на электронную почту пользователя.
 * В этом интерфейсе прописана только сигнатура методов без реализации
 */
public interface EmailMessageService {
    EmailMessageResponseDto sendEmail(@Valid EmailMessageRequestDto request);
    void sendEmail(Task task);
    void sendEmail(Comment comment);

    default void checkRequest(Object object) {
        if (object == null) {
            throw new EmailMessageNotFoundException(EMAIL_MESSAGE_NOT_FOUND_EXCEPTION_MESSAGE);
        }
    }
}
