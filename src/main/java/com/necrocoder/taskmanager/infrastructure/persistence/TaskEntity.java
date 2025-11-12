package com.necrocoder.taskmanager.infrastructure.persistence;

import com.necrocoder.taskmanager.domain.model.Task;
import com.necrocoder.taskmanager.domain.model.valueobject.TaskStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;


import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@AllArgsConstructor
@Table(name = "tasks")
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String title;
    private String description;
    private LocalDateTime dueDate;
    private String priority;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    private LocalDateTime createdAt;


    public TaskEntity() {
        // This method is necesary for JPA to be empty
    }

    public static TaskEntity from(Task task) {
        return new TaskEntity(
                task.getTaskId(),
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                task.getPriority(),
                task.getStatus(),
                task.getCreatedAt()
        );
    }
}