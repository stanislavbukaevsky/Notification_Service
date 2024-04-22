package com.github.stanislavbukaevsky.notificationservice.kafka;

/**
 * Сервис-интерфейс с методами для Kafka в приложении.
 * В этом интерфейсе прописана только сигнатура методов без реализации
 */
public interface KafkaReceiverService {
    void acceptTask();
}
