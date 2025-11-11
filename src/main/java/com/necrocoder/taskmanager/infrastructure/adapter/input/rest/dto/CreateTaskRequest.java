package com.necrocoder.taskmanager.infrastructure.adapter.input.rest.dto;

import jakarta.validation.constraints.NotNull;

public record CreateTaskRequest(
        @NotNull
        String title,
        String description,
        String dueDate,
        String priority
) {
}
