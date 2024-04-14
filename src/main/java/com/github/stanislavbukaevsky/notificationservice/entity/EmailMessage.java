package com.github.stanislavbukaevsky.notificationservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
 * Класс-сущность для всех сообщений на электронную почту
 */
@Entity
@Table(name = EmailMessage.TABLE_NAME)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter(AccessLevel.PUBLIC)
@ToString
public class EmailMessage {
    public static final String TABLE_NAME = "email_messages";
    public static final String ID = "id";
    public static final String RECIPIENT = "recipient";
    public static final String TITLE = "title";
    public static final String TEXT = "text";
    public static final String DATE_TIME = "date_time";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = EmailMessage.ID)
    private Long id;
    @Column(name = EmailMessage.RECIPIENT)
    private String recipient;
    @Column(name = EmailMessage.TITLE)
    private String title;
    @Column(name = EmailMessage.TEXT)
    private String text;
    @Column(name = EmailMessage.DATE_TIME)
    private LocalDateTime dateTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmailMessage that = (EmailMessage) o;
        return Objects.equals(id, that.id) && Objects.equals(recipient, that.recipient) && Objects.equals(title, that.title) && Objects.equals(text, that.text) && Objects.equals(dateTime, that.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, recipient, title, text, dateTime);
    }
}
