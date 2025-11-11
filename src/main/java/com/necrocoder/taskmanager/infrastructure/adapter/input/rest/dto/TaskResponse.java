package com.necrocoder.taskmanager.infrastructure.adapter.input.rest.dto;

import com.necrocoder.taskmanager.domain.model.Task;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskResponse(UUID taskId,
                           String title,
                           String description,
                           LocalDateTime dueDate,
                           String priority,
                           String status) {
    public static TaskResponse from(Task task) {
        return new TaskResponse(
                task.getTaskId(),
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                task.getPriority(),
                task.getStatus().name()
        );
    }

}
