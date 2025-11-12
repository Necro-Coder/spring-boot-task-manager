package com.necrocoder.taskmanager.application.service;

import com.necrocoder.taskmanager.domain.model.Task;
import com.necrocoder.taskmanager.domain.port.in.GetTaskByDescription;
import com.necrocoder.taskmanager.domain.port.out.TaskRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GetTaskByDescriptionService implements GetTaskByDescription {
    private final TaskRepositoryPort taskRepositoryPort;

    public GetTaskByDescriptionService(TaskRepositoryPort taskRepositoryPort) {
        this.taskRepositoryPort = taskRepositoryPort;
    }


    @Override
    public Optional<List<Task>> getTaskByDescription(String description) {
        return taskRepositoryPort.findByDescriptionContaining(description);
    }
}
