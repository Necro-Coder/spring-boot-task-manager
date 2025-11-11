package com.necrocoder.taskmanager.application.service;

import com.necrocoder.taskmanager.application.dto.UpdateTaskCommand;
import com.necrocoder.taskmanager.domain.exception.TaskNotFoundException;
import com.necrocoder.taskmanager.domain.model.Task;
import com.necrocoder.taskmanager.domain.port.in.UpdateTaskUseCase;
import com.necrocoder.taskmanager.domain.port.out.TaskRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UpdateTaskService implements UpdateTaskUseCase {

    private final TaskRepositoryPort taskRepositoryPort;

    public UpdateTaskService(TaskRepositoryPort taskRepositoryPort) {
        this.taskRepositoryPort = taskRepositoryPort;
    }

    @Override
    public Task execute(UUID taskId, UpdateTaskCommand command) {
        Task taskToUpdate = taskRepositoryPort.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException(taskId));

        taskToUpdate.updateTitle(command.title());
        taskToUpdate.updateDescription(command.description());
        taskToUpdate.updateDueDate(command.dueDate());
        taskToUpdate.updatePriority(command.priority());
        return taskRepositoryPort.save(taskToUpdate);
    }
}
