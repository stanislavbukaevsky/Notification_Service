package com.github.stanislavbukaevsky.notificationservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Класс-DTO для ответа пользователю об отправки сообщения по электронной почте
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter(AccessLevel.PUBLIC)
@ToString
@Schema(description = "Объект для отправки сообщения по электронной почте для ответа пользователю")
public class EmailMessageResponseDto {
    @Schema(description = "Уникальный идентификатор сообщения")
    private Long id;
    @Schema(description = "Электронная почта пользователя, куда будет отправлено письмо с уведомлением")
    private String recipient;
    @Schema(description = "Заголовок сообщения")
    private String title;
    @Schema(description = "Тело сообщения")
    private String text;
    @Schema(description = "Дата и время отправки сообщения по электронной почте")
    private LocalDateTime dateTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmailMessageResponseDto that = (EmailMessageResponseDto) o;
        return Objects.equals(id, that.id) && Objects.equals(recipient, that.recipient) && Objects.equals(title, that.title) && Objects.equals(text, that.text) && Objects.equals(dateTime, that.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, recipient, title, text, dateTime);
    }
}
