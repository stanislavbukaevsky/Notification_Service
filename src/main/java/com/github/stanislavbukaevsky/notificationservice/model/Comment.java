package com.github.stanislavbukaevsky.notificationservice.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

/**
 * Модель представления созданного комментария
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter(AccessLevel.PUBLIC)
@ToString
@Schema(description = "Объект созданного комментария пользователем")
public class Comment {
    @Schema(description = "Уникальный идентификатор комментария")
    private Long idComment;
    @Schema(description = "Текст комментария")
    private String textComment;
    @Schema(description = "Уникальный идентификатор задачи")
    private Long taskId;
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
        Comment comment = (Comment) o;
        return Objects.equals(idComment, comment.idComment) && Objects.equals(textComment, comment.textComment) && Objects.equals(taskId, comment.taskId) && Objects.equals(idUser, comment.idUser) && Objects.equals(nameUser, comment.nameUser) && Objects.equals(secondName, comment.secondName) && Objects.equals(email, comment.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idComment, textComment, taskId, idUser, nameUser, secondName, email);
    }
}
