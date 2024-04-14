package com.github.stanislavbukaevsky.notificationservice.repository;

import com.github.stanislavbukaevsky.notificationservice.entity.EmailMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Интерфейс-репозиторий для работы с методами всех сообщений на электронную почту.
 * Наследуется от интерфейса {@link JpaRepository}. Параметры:
 * {@link EmailMessage} - класс-сущность
 * {@link Long} - идентификатор
 */
@Repository
public interface EmailMessageRepository extends JpaRepository<EmailMessage, Long> {
}
