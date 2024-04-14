package com.github.stanislavbukaevsky.notificationservice.service;

import com.github.stanislavbukaevsky.notificationservice.dto.EmailMessageRequestDto;
import com.github.stanislavbukaevsky.notificationservice.dto.EmailMessageResponseDto;
import com.github.stanislavbukaevsky.notificationservice.model.Task;
import jakarta.validation.Valid;

/**
 * Сервис-интерфейс для отправки уведомления на электронную почту пользователя.
 * В этом интерфейсе прописана только сигнатура методов без реализации
 */
public interface EmailMessageService {
    EmailMessageResponseDto sendEmail(@Valid EmailMessageRequestDto request);
    void sendEmail(Task task);
}
