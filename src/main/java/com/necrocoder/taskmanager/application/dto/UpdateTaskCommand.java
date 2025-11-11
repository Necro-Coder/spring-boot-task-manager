package com.necrocoder.taskmanager.application.dto;

import java.time.LocalDateTime;

public record UpdateTaskCommand(String title, String description, LocalDateTime dueDate, String priority, String status) {
}
