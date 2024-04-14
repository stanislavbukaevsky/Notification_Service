package com.github.stanislavbukaevsky.notificationservice.configuration;

import com.github.stanislavbukaevsky.notificationservice.parcer.TextXPath;
import com.jcabi.xml.XML;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.github.stanislavbukaevsky.notificationservice.constant.LoggerTextMessageConstant.ADD_ASSIGN_LISTENER_MESSAGE_LOGGER_CONFIGURATION;
import static com.github.stanislavbukaevsky.notificationservice.constant.LoggerTextMessageConstant.ADD_REVOKE_LISTENER_MESSAGE_LOGGER_CONFIGURATION;

/**
 * Конфигурационный класс с основной настройкой Consumer
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
public class KafkaReceiverConfiguration {
    private final XML setting;
    @Value("${spring.kafka.bootstrap-servers}")
    private String server;
    @Value("${spring.json.trusted}")
    private String trusted;
    @Value("${spring.kafka.topic}")
    private List<String> topics;

    /**
     * Этот метод создает мапу с основными настройками Consumer
     *
     * @return Возвращает мапу с настройками получателя сообщений
     */
    @Bean
    public Map<String, Object> receiverProperties() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, server);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, new TextXPath(this.setting, "//groupId").toString());
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, new TextXPath(this.setting, "//keyDeserializer").toString());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, new TextXPath(this.setting, "//valueDeserializer").toString());
        props.put(trusted, new TextXPath(this.setting, "//trustedPackages").toString());

        return props;
    }

    /**
     * Этот метод настраивает опции, управляющие поведением Consumer
     *
     * @return Возвращает созданный и настроенный получатель сообщений
     */
    @Bean
    public ReceiverOptions<String, Object> receiverOptions() {
        ReceiverOptions<String, Object> receiverOptions = ReceiverOptions.create(receiverProperties());

        return receiverOptions
                .subscription(topics)
                .addAssignListener(partitions ->
                        log.info(ADD_ASSIGN_LISTENER_MESSAGE_LOGGER_CONFIGURATION, partitions))
                .addRevokeListener(partitions ->
                        log.info(ADD_REVOKE_LISTENER_MESSAGE_LOGGER_CONFIGURATION, partitions));
    }

    /**
     * Этот метод создает и настраивает реактивный получатель сообщений
     *
     * @return Возвращает настроенный получатель сообщений
     */
    @Bean
    public KafkaReceiver<String, Object> receiver() {
        return KafkaReceiver.create(receiverOptions());
    }
}
