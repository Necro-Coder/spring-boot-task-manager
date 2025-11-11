package com.necrocoder.taskmanager.infrastructure.adapter.input.rest.mapper;

import com.necrocoder.taskmanager.application.dto.CreateTaskCommand;
import com.necrocoder.taskmanager.application.dto.UpdateTaskCommand;
import com.necrocoder.taskmanager.infrastructure.adapter.input.rest.dto.CreateTaskRequest;
import com.necrocoder.taskmanager.infrastructure.adapter.input.rest.dto.UpdateTaskRequest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TaskRestMapper {
    public static CreateTaskCommand toCreateCommand(CreateTaskRequest req) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return new CreateTaskCommand(
                req.title(),
                req.description(),
                LocalDateTime.parse(req.dueDate(), formatter),
                req.priority()
        );
    }

    public static UpdateTaskCommand toUpdateCommand(UpdateTaskRequest req) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return new UpdateTaskCommand(
                req.title(),
                req.description(),
                LocalDateTime.parse(req.dueDate(), formatter),
                req.priority(),
                req.status()
        );
    }
}
