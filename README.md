# Сервис для отправки уведомления на электронную почту пользователя

### **Задача**

Разработать REST Full API микросервис, который принимает данные о созданной задачи из топика Kafka
и отправляет уведомление на электронную почту пользователя.
Весь проект:

**ссылки на github**

**В проекте реализован следующий функционал:**

- Получение из очереди Kafka данных о созданной задачи.
- Отправка уведомления на электронную почту пользователя.
- Сохранение уведомления в базе данных.
- Написаны тесты для сервисного слоя.

***

## **Функционал**

### В приложении использован следующий функционал:

- Java 17;
- Spring Boot 3.2.4;
- Maven;
- JPA;
- Validation;
- Mail;
- Liquibase;
- Mapstruct;
- Lombok;
- Apache Kafka;
- Reactor Kafka;
- PostgresSQL;
- Swagger-UI.

## **Запуск приложения**

### Для запуска приложения выполните следующие действия

**1. Настройка файла application.yaml**

- Для работы приложения вам нужно правильно настроить
  файл **[application.yaml](src/main/resources/application.yaml)**.
  Открыв этот файл вы увидете множество заглушек, которые нужно описать.
- Создайте в корне проекта файл с названием **.env** (не забудьте символ точки перед названием файла!) и опишите все
  значения переменных как это показано ниже

```
SPRING_DATASOURCE_HOST=Локальный хостинг вашего сервера
SPRING_DATASOURCE_PORT=Порт вашего сервера
SPRING_DATASOURCE_DATABASE=Название базы данных
SPRING_DATASOURCE_USERNAME=Имя пользователя базы данных
SPRING_DATASOURCE_PASSWORD=Пароль пользователя базы данных
SPRING_MAIL_HOST=SMTP хост электронной почты отправителя
SPRING_MAIL_PORT=Порт электронной почты отправителя
SPRING_MAIL_USERNAME=Электронная почта отправителя
SPRING_MAIL_PASSWORD=Пароль от электронной почты отправителя
SPRING_KAFKA_TOPIC=Название топика для Kafka
SPRING_KAFKA_SERVER_HOST=Локальный хостинг вашего сервера Kafka
SPRING_KAFKA_SERVER_PORT=Порт вашего сервера Kafka
SERVER_PORT=Порт, на котором будет работать приложение
```

**2. Подключения сервера Kafka**

- Скачайте и установите **[Kafka](https://kafka.apache.org/quickstart)** к себе на компьютер.
- Запустите сервер zookeeper и kafka.

**3. Запуск приложения**

- Теперь запустите приложение в
  файле [NotificationServiceApplication](src/main/java/com/github/stanislavbukaevsky/notificationservice/NotificationServiceApplication.java).

***

## **Разработка**

### В реализации проекта принимал участие:

- Букаевский Станислав - [ссылка на GitHub](https://github.com/stanislavbukaevsky)