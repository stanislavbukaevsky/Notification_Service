package com.github.stanislavbukaevsky.notificationservice.util;

import java.time.LocalDateTime;

/**
 * Запись, для построения исключений связанных с валидацией, с полной информацией о них
 *
 * @param fieldName имя поля, где произошло исключение
 * @param message   сообщение с ошибкой
 * @param dateTime  дата и время, когда произошло исключение
 */
public record Violation(String fieldName, String message, LocalDateTime dateTime) {
}
