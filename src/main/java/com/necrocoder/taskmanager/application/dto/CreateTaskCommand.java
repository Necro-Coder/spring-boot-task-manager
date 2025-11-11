package com.necrocoder.taskmanager.application.dto;

import java.time.LocalDateTime;

public record CreateTaskCommand(String title, String description, LocalDateTime dueDate, String priority) {
}
