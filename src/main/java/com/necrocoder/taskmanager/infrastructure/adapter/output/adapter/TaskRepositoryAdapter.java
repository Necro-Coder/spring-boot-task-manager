package com.necrocoder.taskmanager.infrastructure.adapter.output.adapter;

import com.necrocoder.taskmanager.domain.model.Task;
import com.necrocoder.taskmanager.domain.port.out.TaskRepositoryPort;
import com.necrocoder.taskmanager.infrastructure.adapter.output.persistence.SpringDataTaskRepository;
import com.necrocoder.taskmanager.infrastructure.persistence.TaskEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class TaskRepositoryAdapter implements TaskRepositoryPort {

    private final SpringDataTaskRepository springDataTaskRepository;

    public TaskRepositoryAdapter(SpringDataTaskRepository springDataTaskRepository) {
        this.springDataTaskRepository = springDataTaskRepository;
    }

    @Override
    public List<Task> findAll() {
        List<TaskEntity> tasks = springDataTaskRepository.findAll();
        return tasks.stream().map(Task::from).toList();
    }

    @Override
    public Optional<Task> findById(UUID id) {
        return springDataTaskRepository.findById(id).map(Task::from);
    }

    @Override
    public Optional<List<Task>> findByDescriptionContaining(String keyword) {
        return Optional.empty();
    }

    @Override
    public Task save(Task task) {
        TaskEntity taskEntity = springDataTaskRepository.save(TaskEntity.from(task));
        return Task.from(taskEntity);
    }

    @Override
    public Optional<Task> update(UUID id, Task task) {
        return this.findById(id)
                .map(existing -> {
                    TaskEntity taskToUpdate = TaskEntity.from(task);
                    TaskEntity saved = springDataTaskRepository.save(taskToUpdate);
                    return Task.from(saved);
                });
    }

    @Override
    public void deleteById(UUID id) {
        springDataTaskRepository.deleteById(id);
    }
}
