package com.github.stanislavbukaevsky.notificationservice.mapper;

import com.github.stanislavbukaevsky.notificationservice.dto.EmailMessageRequestDto;
import com.github.stanislavbukaevsky.notificationservice.dto.EmailMessageResponseDto;
import com.github.stanislavbukaevsky.notificationservice.entity.EmailMessage;
import com.github.stanislavbukaevsky.notificationservice.model.Comment;
import com.github.stanislavbukaevsky.notificationservice.model.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Маппер-интерфейс, который преобразует информацию о сообщении в DTO
 */
@Mapper
public interface EmailMessageMapper {
    @Mapping(ignore = true, target = "title")
    @Mapping(ignore = true, target = "text")
    EmailMessage mappingEntityEmailMessage(EmailMessageRequestDto request);
    @Mapping(source = "email", target = "recipient")
    EmailMessage mappingEntityEmailMessage(Task task);
    @Mapping(source = "email", target = "recipient")
    EmailMessage mappingEntityEmailMessage(Comment comment);
    EmailMessageResponseDto mappingEmailMessageResponseDto(EmailMessage emailMessage);
}
