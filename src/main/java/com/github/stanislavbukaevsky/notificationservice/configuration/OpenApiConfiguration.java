package com.github.stanislavbukaevsky.notificationservice.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

/**
 * Конфигурационный класс для настройки web интерфейса в Swagger
 */
@Configuration
@OpenAPIDefinition(info =
@Info(title = "Сервис с уведомлениями по электронной почте", description = "Web интерфейс для отправки уведомления на электронную почту в приложении", contact =
@Contact(name = "Букаевский Станислав", email = "s.bukaevskiq@astondevs.ru")))
public class OpenApiConfiguration {
}
