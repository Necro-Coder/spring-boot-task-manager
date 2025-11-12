package com.necrocoder.taskmanager.domain.port.out;

import com.necrocoder.taskmanager.domain.model.Task;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskRepositoryPort {
    List<Task> findAll();

    Optional<Task> findById(UUID id);

    Optional<List<Task>> findByDescriptionContaining(String keyword);

    Optional<Task> update(UUID id, Task task);
    Task save(Task task);
    void deleteById(UUID id);
}
