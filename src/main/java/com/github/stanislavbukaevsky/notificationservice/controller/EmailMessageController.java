package com.github.stanislavbukaevsky.notificationservice.controller;

import com.github.stanislavbukaevsky.notificationservice.dto.EmailMessageRequestDto;
import com.github.stanislavbukaevsky.notificationservice.dto.EmailMessageResponseDto;
import com.github.stanislavbukaevsky.notificationservice.service.EmailMessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Класс-контроллер для работы с методами отправки уведомления на электронную почту пользователя
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/email-message")
@Validated
@Tag(name = "Работа с уведомлениями по электронной почте",
        description = "Позволяет управлять методами по работе с отправкой уведомления на электронную почту в приложении")
public class EmailMessageController {
    private final EmailMessageService emailService;

    /**
     * Этот метод позволяет отправить уведомление на электронную почту пользователя,
     * который создал новую задачу в приложении.
     * Этот метод получает данные о созданной задачи из топика Kafka.
     *
     * @param request запрос от пользователя
     * @return Возвращает ответ пользователю с информацией об отправленном письме на электронную почту
     */
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Письмо отправлено на электронную почту (OK)",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = EmailMessageResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Неккоректный запрос (Bad Request)"),
            @ApiResponse(responseCode = "401", description = "Неаутентифицированный пользователь (Unauthorized)"),
            @ApiResponse(responseCode = "403", description = "Пользователю запрещен вход на этот ресурс (Forbidden)"),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден (Not Found)"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера (Internal Server Error)")
    })
    @Operation(summary = "Метод для отправления уведомления на электронную почту пользователя",
            description = "Позволяет отправить уведомление на электронную почту пользователя, который создал новую задачу в приложении")
    @PostMapping(value = "/send", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmailMessageResponseDto> sendEmail(@RequestBody @Valid EmailMessageRequestDto request) {
        EmailMessageResponseDto response = emailService.sendEmail(request);
        return ResponseEntity.ok(response);
    }
}
