package com.github.stanislavbukaevsky.notificationservice.kafka.impl;

import com.github.stanislavbukaevsky.notificationservice.kafka.KafkaReceiverService;
import com.github.stanislavbukaevsky.notificationservice.model.Task;
import com.github.stanislavbukaevsky.notificationservice.parcer.LocalDateTimeDeserializer;
import com.github.stanislavbukaevsky.notificationservice.service.EmailMessageService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.kafka.receiver.KafkaReceiver;

import java.time.LocalDateTime;

import static com.github.stanislavbukaevsky.notificationservice.constant.LoggerTextMessageConstant.KAFKA_ACCEPT_MESSAGE_LOGGER_SERVICE;

/**
 * Сервис-класс с бизнес-логикой для Kafka в приложении.
 * Реализует интерфейс {@link KafkaReceiverService}
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaReceiverServiceImpl implements KafkaReceiverService {
    private final KafkaReceiver<String, Object> kafkaReceiver;
    private final EmailMessageService emailMessageService;
    private final LocalDateTimeDeserializer localDateTimeDeserializer;

    /**
     * Этот метод запускается сразу же после загрузки всего приложения
     */
    @PostConstruct
    public void init() {
        accept();
    }

    /**
     * Реализация метода для получения сообщений из топика Kafka
     */
    @Override
    public void accept() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, localDateTimeDeserializer)
                .create();

        kafkaReceiver.receive()
                .subscribe(r -> {
                    log.info(KAFKA_ACCEPT_MESSAGE_LOGGER_SERVICE, r.value());
                    Task task = gson.fromJson(r.value().toString(), Task.class);
                    emailMessageService.sendEmail(task);
                    r.receiverOffset().acknowledge();
                });
    }
}
