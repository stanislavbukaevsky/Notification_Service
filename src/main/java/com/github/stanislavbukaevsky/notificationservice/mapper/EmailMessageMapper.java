package com.github.stanislavbukaevsky.notificationservice.mapper;

import com.github.stanislavbukaevsky.notificationservice.dto.EmailMessageRequestDto;
import com.github.stanislavbukaevsky.notificationservice.dto.EmailMessageResponseDto;
import com.github.stanislavbukaevsky.notificationservice.entity.EmailMessage;
import com.github.stanislavbukaevsky.notificationservice.model.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Маппер-интерфейс, который преобразует информацию о сообщении в DTO
 */
@Mapper
public interface EmailMessageMapper {
    EmailMessage mappingEntityEmailMessage(EmailMessageRequestDto request);
    @Mapping(source = "email", target = "recipient")
    EmailMessage mappingEntityEmailMessage(Task task);
    EmailMessageResponseDto mappingEmailMessageResponseDto(EmailMessage emailMessage);
}
