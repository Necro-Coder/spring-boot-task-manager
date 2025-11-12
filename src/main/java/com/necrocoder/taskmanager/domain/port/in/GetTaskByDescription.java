package com.necrocoder.taskmanager.domain.port.in;

import com.necrocoder.taskmanager.domain.model.Task;

import java.util.List;
import java.util.Optional;

public interface GetTaskByDescription {
    Optional<List<Task>> getTaskByDescription(String description);
}
