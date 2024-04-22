package com.github.stanislavbukaevsky.notificationservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDateTime;

/**
 * Запись для ответа пользователю об отправки сообщения по электронной почте
 */
@Builder
@Schema(description = "Объект для отправки сообщения по электронной почте для ответа пользователю")
public record EmailMessageResponseDto(
        @Schema(description = "Уникальный идентификатор сообщения")
        Long id,
        @Schema(description = "Электронная почта пользователя, куда будет отправлено письмо с уведомлением")
        String recipient,
        @Schema(description = "Заголовок сообщения")
        String title,
        @Schema(description = "Тело сообщения")
        String text,
        @Schema(description = "Дата и время отправки сообщения по электронной почте")
        LocalDateTime dateTime
) {
}
