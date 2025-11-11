package com.necrocoder.taskmanager.infrastructure.adapter.input.rest.dto;

import jakarta.validation.constraints.NotNull;

public record UpdateTaskRequest(String title,
                                String description,
                                String dueDate,
                                String priority,
                                String status) {
}
