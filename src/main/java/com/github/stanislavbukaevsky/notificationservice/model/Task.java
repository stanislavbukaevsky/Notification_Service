package com.github.stanislavbukaevsky.notificationservice.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Модель представления созданной задачи
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter(AccessLevel.PUBLIC)
@ToString
@Schema(description = "Объект созданной задачи пользователем")
public class Task {
    @Schema(description = "Уникальный идентификатор задачи")
    private Long idTask;
    @Schema(description = "Название задачи")
    private String nameTask;
    @Schema(description = "Подробное описание задачи")
    private String description;
    @Schema(description = "Дата и время создание задачи")
    private LocalDateTime dateTimeCreatedTask;
    @Schema(description = "Приоритет задачи")
    private String priority;
    @Schema(description = "Статус задачи")
    private String status;
    @Schema(description = "Уникальный идентификатор пользователя")
    private Long idUser;
    @Schema(description = "Имя пользователя")
    private String nameUser;
    @Schema(description = "Фамилия пользователя")
    private String secondName;
    @Schema(description = "Электронная почта пользователя")
    private String email;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(idTask, task.idTask) && Objects.equals(nameTask, task.nameTask) && Objects.equals(description, task.description) && Objects.equals(dateTimeCreatedTask, task.dateTimeCreatedTask) && Objects.equals(priority, task.priority) && Objects.equals(status, task.status) && Objects.equals(idUser, task.idUser) && Objects.equals(nameUser, task.nameUser) && Objects.equals(secondName, task.secondName) && Objects.equals(email, task.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTask, nameTask, description, dateTimeCreatedTask, priority, status, idUser, nameUser, secondName, email);
    }
}
