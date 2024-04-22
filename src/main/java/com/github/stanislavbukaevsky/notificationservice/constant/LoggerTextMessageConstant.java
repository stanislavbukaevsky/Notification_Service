package com.github.stanislavbukaevsky.notificationservice.constant;

/**
 * Этот класс содержит текстовые константные переменные для всех логов в приложении
 */
public class LoggerTextMessageConstant {
    // Логи для методов в конфигурационных файлах
    public static final String ADD_ASSIGN_LISTENER_MESSAGE_LOGGER_CONFIGURATION = "Добавлен новый слушатель для получения новых сообщений. Раздел получения сообщений: {}";
    public static final String ADD_REVOKE_LISTENER_MESSAGE_LOGGER_CONFIGURATION = "Слушатель перестал принимать новые сообщения. Раздел сообщений: {}";

    // Логи для методов в сервисах
    public static final String KAFKA_ACCEPT_TASK_MESSAGE_LOGGER_SERVICE = "Вызван метод для получения сообщений из топика Kafka для создания задач. Получено новое сообщение из топика: {}";
    public static final String KAFKA_ACCEPT_COMMENT_MESSAGE_LOGGER_SERVICE = "Вызван метод для получения сообщений из топика Kafka для создания комментария. Получено новое сообщение из топика: {}";
    public static final String DESERIALIZE_MESSAGE_LOGGER_SERVICE = "Вызван метод для десериализации даты и времени из JSON в объект LocalDateTime в сервисе";
    public static final String SEND_EMAIL_MESSAGE_LOGGER_SERVICE = "Вызван метод для отправки уведомления на электронную почту пользователя. Запрос от пользователя: {}";
}
