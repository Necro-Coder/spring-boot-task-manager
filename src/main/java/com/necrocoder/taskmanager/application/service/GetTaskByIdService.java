package com.necrocoder.taskmanager.application.service;

import com.necrocoder.taskmanager.domain.exception.TaskNotFoundException;
import com.necrocoder.taskmanager.domain.model.Task;
import com.necrocoder.taskmanager.domain.port.in.GetTaskByIdUseCase;
import com.necrocoder.taskmanager.domain.port.out.TaskRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GetTaskByIdService implements GetTaskByIdUseCase {
    private final TaskRepositoryPort taskRepositoryPort;

    public GetTaskByIdService(TaskRepositoryPort taskRepositoryPort) {
        this.taskRepositoryPort = taskRepositoryPort;
    }

    @Override
    public Task execute(UUID taskId) {
        return taskRepositoryPort.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException(taskId));
    }
}
