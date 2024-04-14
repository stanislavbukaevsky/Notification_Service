package com.github.stanislavbukaevsky.notificationservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

/**
 * Класс-DTO для запроса от пользователя на отправку сообщения по электронной почте
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter(AccessLevel.PUBLIC)
@ToString
@Schema(description = "Объект для отправки сообщения по электронной почте с запросом от пользователя")
public class EmailMessageRequestDto {
    @NotEmpty(message = "Поле электронной почты не должно быть пустым!")
    @Size(min = 6, max = 64, message = "Электронная почта должна содержать от 6 до 64 символов!")
    @Email(message = "Электронная почта должна быть следующего вида: test@test.ru")
    @Schema(description = "Электронная почта пользователя, куда будет отправлено письмо с уведомлением")
    private String recipient;
    @NotEmpty(message = "Поле заголовка сообщения не должно быть пустым!")
    @Size(min = 3, max = 128, message = "Заголовок сообщения должен содержать от 3 до 128 символов!")
    @Schema(description = "Заголовок сообщения")
    private String title;
    @NotEmpty(message = "Поле тела сообщения не должно быть пустым!")
    @Size(min = 3, max = 2048, message = "Тело сообщения должно содержать от 3 до 2048 символов!")
    @Schema(description = "Тело сообщения")
    private String text;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmailMessageRequestDto that = (EmailMessageRequestDto) o;
        return Objects.equals(recipient, that.recipient) && Objects.equals(title, that.title) && Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recipient, title, text);
    }
}
