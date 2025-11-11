package com.necrocoder.taskmanager.application.service;

import com.necrocoder.taskmanager.domain.model.Task;
import com.necrocoder.taskmanager.domain.port.in.GetAllTasksUseCase;
import com.necrocoder.taskmanager.domain.port.out.TaskRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllTasksService implements GetAllTasksUseCase {
    private final TaskRepositoryPort taskRepositoryPort;

    public GetAllTasksService(TaskRepositoryPort taskRepositoryPort) {
        this.taskRepositoryPort = taskRepositoryPort;
    }
    @Override
    public List<Task> execute() {
        return taskRepositoryPort.findAll();
    }
}
