package com.necrocoder.taskmanager.domain.port.in;

import com.necrocoder.taskmanager.domain.model.Task;

import java.util.List;

public interface GetAllTasksUseCase {
    List<Task> execute();
}
