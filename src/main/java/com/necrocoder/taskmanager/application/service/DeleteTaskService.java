package com.necrocoder.taskmanager.application.service;

import com.necrocoder.taskmanager.domain.exception.TaskNotFoundException;
import com.necrocoder.taskmanager.domain.port.in.DeleteTaskUseCase;
import com.necrocoder.taskmanager.domain.port.out.TaskRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeleteTaskService implements DeleteTaskUseCase {
    private final TaskRepositoryPort taskRepositoryPort;

    public DeleteTaskService(TaskRepositoryPort taskRepositoryPort) {
        this.taskRepositoryPort = taskRepositoryPort;
    }

    @Override
    public void execute(UUID taskId) {
        taskRepositoryPort.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException(taskId));

        taskRepositoryPort.deleteById(taskId);
    }
}
