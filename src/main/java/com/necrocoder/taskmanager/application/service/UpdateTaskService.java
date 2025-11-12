package com.necrocoder.taskmanager.application.service;

import com.necrocoder.taskmanager.application.dto.UpdateTaskCommand;
import com.necrocoder.taskmanager.domain.exception.TaskNotFoundException;
import com.necrocoder.taskmanager.domain.model.Task;
import com.necrocoder.taskmanager.domain.port.in.UpdateTaskUseCase;
import com.necrocoder.taskmanager.domain.port.out.TaskRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UpdateTaskService implements UpdateTaskUseCase {

    private final TaskRepositoryPort taskRepositoryPort;

    public UpdateTaskService(TaskRepositoryPort taskRepositoryPort) {
        this.taskRepositoryPort = taskRepositoryPort;
    }

    @Override
    public Optional<Task> execute(UUID taskId, UpdateTaskCommand command) {
        taskRepositoryPort.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException(taskId));

        Task task = Task.create(
                command.title(),
                command.description(),
                command.dueDate(),
                command.priority()
        );

        return taskRepositoryPort.update(taskId, task);
    }
}
