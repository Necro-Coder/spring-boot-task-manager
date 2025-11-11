package com.necrocoder.taskmanager.application.service;

import com.necrocoder.taskmanager.application.dto.CreateTaskCommand;
import com.necrocoder.taskmanager.domain.model.Task;
import com.necrocoder.taskmanager.domain.port.in.CreateTaskUseCase;
import com.necrocoder.taskmanager.domain.port.out.TaskRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class CreateTaskService implements CreateTaskUseCase {
    private final TaskRepositoryPort taskRepositoryPort;

    public CreateTaskService(TaskRepositoryPort taskRepositoryPort) {
        this.taskRepositoryPort = taskRepositoryPort;
    }
    @Override
    public Task execute(CreateTaskCommand command) {
        Task task = Task.create(
                command.title(),
                command.description(),
                command.dueDate(),
                command.priority()
        );
        taskRepositoryPort.save(task);
        return task;
    }
}
