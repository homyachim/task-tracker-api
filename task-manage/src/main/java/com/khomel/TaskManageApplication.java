package com.khomel;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@OpenAPIDefinition(
        info = @Info(
                title = "Task Manage API",
                description = "Сервис управления задачами позволяет создавать, обновлять, отслеживать и удалять " +
                        "задачи в системе. Он обеспечивает централизованное хранение информации о задачах, включая " +
                        "их статус, приоритет, сроки выполнения и исполнителей. Сервис тесно взаимодействует с " +
                        "другими компонентами, такими как сервис уведомлений, чтобы уведомлять пользователей об " +
                        "изменениях в задачах и обеспечивать эффективное управление рабочими процессами.",
                version = "1.0.0"
        )
)
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@EntityScan(basePackages = "com.khomel.model.entity")
@EnableJpaRepositories(basePackages = "com.khomel.repository")
public class TaskManageApplication {
    public static void main(String[] args) {
        SpringApplication.run(TaskManageApplication.class, args);
    }
}