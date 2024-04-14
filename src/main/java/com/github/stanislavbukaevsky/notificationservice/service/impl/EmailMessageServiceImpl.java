package com.github.stanislavbukaevsky.notificationservice.service.impl;

import com.github.stanislavbukaevsky.notificationservice.dto.EmailMessageRequestDto;
import com.github.stanislavbukaevsky.notificationservice.dto.EmailMessageResponseDto;
import com.github.stanislavbukaevsky.notificationservice.entity.EmailMessage;
import com.github.stanislavbukaevsky.notificationservice.exception.EmailMessageNotFoundException;
import com.github.stanislavbukaevsky.notificationservice.mapper.EmailMessageMapper;
import com.github.stanislavbukaevsky.notificationservice.model.Task;
import com.github.stanislavbukaevsky.notificationservice.repository.EmailMessageRepository;
import com.github.stanislavbukaevsky.notificationservice.service.EmailMessageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;

import static com.github.stanislavbukaevsky.notificationservice.constant.DescriptionTextMessageConstant.SEND_EMAIL_TEXT_MESSAGE_DESCRIPTION;
import static com.github.stanislavbukaevsky.notificationservice.constant.DescriptionTextMessageConstant.SEND_EMAIL_TEXT_MESSAGE_DESCRIPTION_2;
import static com.github.stanislavbukaevsky.notificationservice.constant.DescriptionTextMessageConstant.SEND_EMAIL_TITLE_MESSAGE_DESCRIPTION;
import static com.github.stanislavbukaevsky.notificationservice.constant.ExceptionTextMessageConstant.EMAIL_MESSAGE_NOT_FOUND_EXCEPTION_MESSAGE;
import static com.github.stanislavbukaevsky.notificationservice.constant.LoggerTextMessageConstant.SEND_EMAIL_MESSAGE_LOGGER_SERVICE;

/**
 * Сервис-класс с бизнес-логикой для методов отправки уведомления на электронную почту пользователя.
 * Реализует интерфейс {@link EmailMessageService}
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Validated
public class EmailMessageServiceImpl implements EmailMessageService {
    private final JavaMailSender javaMailSender;
    private final EmailMessageMapper emailMessageMapper;
    private final EmailMessageRepository emailMessageRepository;
    @Value("${spring.mail.username}")
    private String sender;

    /**
     * Реализация метода для отправки уведомления на электронную почту пользователя
     *
     * @param request запрос от пользователя
     * @return Возвращает ответ пользователю с информацией об отправленном письме на электронную почту
     */
    @Override
    public EmailMessageResponseDto sendEmail(@Valid EmailMessageRequestDto request) {
        if (request == null) {
            throw new EmailMessageNotFoundException(EMAIL_MESSAGE_NOT_FOUND_EXCEPTION_MESSAGE);
        }

        LocalDateTime dateTime = LocalDateTime.now();
        EmailMessage emailMessage = emailMessageMapper.mappingEntityEmailMessage(request);
        emailMessage.setRecipient(request.getRecipient());
        emailMessage.setTitle(SEND_EMAIL_TITLE_MESSAGE_DESCRIPTION);
        emailMessage.setText(SEND_EMAIL_TEXT_MESSAGE_DESCRIPTION + request.getRecipient() +
                SEND_EMAIL_TEXT_MESSAGE_DESCRIPTION_2 + "тестовая задача.");

        sendEmail(dateTime, emailMessage);
        EmailMessage result = emailMessageRepository.save(emailMessage);
        log.info(SEND_EMAIL_MESSAGE_LOGGER_SERVICE, request);
        return emailMessageMapper.mappingEmailMessageResponseDto(result);
    }

    /**
     * Реализация перегруженного метода для отправки уведомления на электронную почту пользователя.
     * Информацию по созданной задачи метод берет из брокера Kafka.
     *
     * @param task модель созданной задачи
     */
    @Override
    public void sendEmail(Task task) {
        if (task == null) {
            throw new EmailMessageNotFoundException(EMAIL_MESSAGE_NOT_FOUND_EXCEPTION_MESSAGE);
        }

        LocalDateTime dateTime = LocalDateTime.now();
        EmailMessage emailMessage = emailMessageMapper.mappingEntityEmailMessage(task);
        emailMessage.setRecipient(emailMessage.getRecipient());
        emailMessage.setTitle(SEND_EMAIL_TITLE_MESSAGE_DESCRIPTION);
        emailMessage.setText(SEND_EMAIL_TEXT_MESSAGE_DESCRIPTION + task.getNameUser() +
                " " + task.getSecondName() + SEND_EMAIL_TEXT_MESSAGE_DESCRIPTION_2 + task.getEmail());

        sendEmail(dateTime, emailMessage);
        emailMessageRepository.save(emailMessage);
        log.info(SEND_EMAIL_MESSAGE_LOGGER_SERVICE, task);
    }

    private void sendEmail(LocalDateTime dateTime, EmailMessage emailMessage) {
        emailMessage.setDateTime(dateTime);

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(sender);
        simpleMailMessage.setTo(emailMessage.getRecipient());
        simpleMailMessage.setSubject(emailMessage.getTitle());
        simpleMailMessage.setText(emailMessage.getText());
        javaMailSender.send(simpleMailMessage);
    }
}
