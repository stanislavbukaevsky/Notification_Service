package com.github.stanislavbukaevsky.notificationservice.configuration;

import com.jcabi.xml.XML;
import com.jcabi.xml.XMLDocument;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Конфигурационный класс для настройки сериализации Consumer
 */
@Configuration
public class KafkaBeanConfiguration {
    private static final String PATH_FILE = "/kafka/consumer.xml";

    /**
     * Этот метод настраивает сериализацию Consumer, которую парсит из файла xml
     *
     * @return Возвращает преобразованный xml файл
     */
    @Bean
    @SneakyThrows
    public XML consumerXML() {
        return new XMLDocument(getClass().getResourceAsStream(PATH_FILE)
                .readAllBytes());
    }
}
