package com.github.stanislavbukaevsky.notificationservice.kafka.impl;

import com.github.stanislavbukaevsky.notificationservice.kafka.KafkaReceiverService;
import com.github.stanislavbukaevsky.notificationservice.model.Comment;
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

import static com.github.stanislavbukaevsky.notificationservice.constant.LoggerTextMessageConstant.KAFKA_ACCEPT_COMMENT_MESSAGE_LOGGER_SERVICE;
import static com.github.stanislavbukaevsky.notificationservice.constant.LoggerTextMessageConstant.KAFKA_ACCEPT_TASK_MESSAGE_LOGGER_SERVICE;

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
    private final static String TASK_TOPIC = "task-create";
    private final static String COMMENT_TOPIC = "comment-create";

    /**
     * Этот метод запускается сразу же после загрузки всего приложения
     */
    @PostConstruct
    public void init() {
        acceptTask();
    }

    /**
     * Реализация метода для получения сообщений из топика Kafka для созданных задач и комментариев
     */
    @Override
    public void acceptTask() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, localDateTimeDeserializer)
                .create();

        kafkaReceiver.receive()
                .subscribe(r -> {
                        if (r.topic().equals(COMMENT_TOPIC)) {
                            log.info(KAFKA_ACCEPT_COMMENT_MESSAGE_LOGGER_SERVICE, r.value());
                            Comment comment = gson.fromJson(r.value().toString(), Comment.class);
                            emailMessageService.sendEmail(comment);
                            r.receiverOffset().acknowledge();
                        }
                        if (r.topic().equals(TASK_TOPIC)) {
                            log.info(KAFKA_ACCEPT_TASK_MESSAGE_LOGGER_SERVICE, r.value());
                            Task task = gson.fromJson(r.value().toString(), Task.class);
                            emailMessageService.sendEmail(task);
                            r.receiverOffset().acknowledge();
                        }
                });
    }
}
