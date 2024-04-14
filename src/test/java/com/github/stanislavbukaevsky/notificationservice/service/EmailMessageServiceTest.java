package com.github.stanislavbukaevsky.notificationservice.service;

import com.github.stanislavbukaevsky.notificationservice.dto.EmailMessageRequestDto;
import com.github.stanislavbukaevsky.notificationservice.dto.EmailMessageResponseDto;
import com.github.stanislavbukaevsky.notificationservice.entity.EmailMessage;
import com.github.stanislavbukaevsky.notificationservice.exception.EmailMessageNotFoundException;
import com.github.stanislavbukaevsky.notificationservice.mapper.EmailMessageMapper;
import com.github.stanislavbukaevsky.notificationservice.model.Task;
import com.github.stanislavbukaevsky.notificationservice.repository.EmailMessageRepository;
import com.github.stanislavbukaevsky.notificationservice.service.impl.EmailMessageServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.javamail.JavaMailSender;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Класс с тестами для уведомлений по электронной почте
 */
@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EmailMessageServiceTest {
    private final static Long ID = 1L;
    private final static String RECIPIENT = "test@test.ru";
    private final static String TITLE = "Test header";
    private final static String TEXT = "Test text";
    private final static LocalDateTime DATE_TIME = LocalDateTime.now();
    private final static Long ID_TASK = 2L;
    private final static String NAME_TASK = "Test name task";
    private final static String DESCRIPTION = "Test description";
    private final static String PRIORITY = "Test priority";
    private final static String STATUS = "Test status";
    private final static Long ID_USER = 3L;
    private final static String NAME_USER = "Test name user";
    private final static String SECOND_NAME = "Test second name user";
    private final static String PASSWORD = "Test password";
    private final static String ROLE = "Test role";
    @InjectMocks
    private EmailMessageServiceImpl emailMessageService;
    @Mock
    private JavaMailSender javaMailSender;
    @Mock
    private EmailMessageMapper emailMessageMapper;
    @Mock
    private EmailMessageRepository emailMessageRepository;
    private EmailMessage emailMessage;
    private EmailMessageResponseDto response;
    private EmailMessageRequestDto request;
    private Task task;

    @BeforeAll
    public void setUp() {
        emailMessage = EmailMessage.builder()
                .id(ID)
                .recipient(RECIPIENT)
                .title(TITLE)
                .text(TEXT)
                .dateTime(DATE_TIME).build();
        response = EmailMessageResponseDto.builder()
                .id(ID)
                .recipient(RECIPIENT)
                .title(TITLE)
                .text(TEXT)
                .dateTime(DATE_TIME).build();
        request = EmailMessageRequestDto.builder()
                .recipient(RECIPIENT)
                .title(TITLE)
                .text(TEXT).build();
        task = Task.builder()
                .idTask(ID_TASK)
                .nameTask(NAME_TASK)
                .description(DESCRIPTION)
                .dateTimeCreatedTask(DATE_TIME)
                .priority(PRIORITY)
                .status(STATUS)
                .idUser(ID_USER)
                .nameUser(NAME_USER)
                .secondName(SECOND_NAME)
                .email(RECIPIENT)
                .password(PASSWORD)
                .role(ROLE).build();
    }

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        emailMessageService = new EmailMessageServiceImpl(
                javaMailSender,
                emailMessageMapper,
                emailMessageRepository
        );
    }

    @Test
    @DisplayName("Отправка уведомления на электронную почту")
    public void sendEmail_shouldReturnResponse() {
        Assertions.assertNotNull(emailMessageRepository);
        Mockito.lenient().when(emailMessageMapper
                .mappingEntityEmailMessage(ArgumentMatchers.any(EmailMessageRequestDto.class)))
                .thenReturn(emailMessage);
        Mockito.lenient().when(emailMessageRepository.save(ArgumentMatchers.any(EmailMessage.class)))
                .thenReturn(emailMessage);
        Mockito.lenient().when(emailMessageMapper
                .mappingEmailMessageResponseDto(ArgumentMatchers.any(EmailMessage.class)))
                .thenReturn(response);

        EmailMessageResponseDto actual = emailMessageService.sendEmail(request);
        org.assertj.core.api.Assertions.assertThat(actual.getId()).isEqualTo(emailMessage.getId());
    }

    @Test
    @DisplayName("Проверка отправки уведомления на электронную почту, если запрос пользователя null")
    public void sendEmail_ifRequestNull_thenThrow() {
        assertThrows(EmailMessageNotFoundException.class, () ->
                emailMessageService.sendEmail((EmailMessageRequestDto) null));
    }

    @Test
    @DisplayName("Отправка уведомления на электронную почту с приминением Kafka")
    public void sendEmail_shouldReturnResponseKafka() {
        Assertions.assertNotNull(emailMessageRepository);
        Mockito.lenient().when(emailMessageMapper
                        .mappingEntityEmailMessage(ArgumentMatchers.any(Task.class)))
                .thenReturn(emailMessage);
        Mockito.lenient().when(emailMessageRepository.save(ArgumentMatchers.any(EmailMessage.class)))
                .thenReturn(emailMessage);

        emailMessageService.sendEmail(task);
        Assertions.assertEquals(task.getIdTask(), ID_TASK);
        Assertions.assertEquals(task.getNameTask(), NAME_TASK);
        Assertions.assertEquals(task.getDescription(), DESCRIPTION);
        Assertions.assertEquals(task.getPriority(), PRIORITY);
        Assertions.assertEquals(task.getStatus(), STATUS);
        Assertions.assertEquals(task.getIdUser(), ID_USER);
        Assertions.assertEquals(task.getNameUser(), NAME_USER);
        Assertions.assertEquals(task.getSecondName(), SECOND_NAME);
        Assertions.assertEquals(task.getEmail(), RECIPIENT);
        Assertions.assertEquals(task.getPassword(), PASSWORD);
        Assertions.assertEquals(task.getRole(), ROLE);
    }

    @Test
    @DisplayName("Проверка отправки уведомления на электронную почту, если запрос пользователя null")
    public void sendEmail_ifModelNull_thenThrow() {
        assertThrows(EmailMessageNotFoundException.class, () ->
                emailMessageService.sendEmail((Task) null));
    }
}
