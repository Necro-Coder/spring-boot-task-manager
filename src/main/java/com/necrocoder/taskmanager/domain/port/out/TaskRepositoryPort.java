package com.necrocoder.taskmanager.domain.port.out;

import com.necrocoder.taskmanager.domain.model.Task;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskRepositoryPort {
    List<Task> findAll();

    Optional<Task> findById(UUID id);

    Task save(Task task);
    Task update(Task task);
    void deleteById(UUID id);
}
