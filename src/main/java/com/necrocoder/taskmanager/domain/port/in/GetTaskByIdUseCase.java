package com.necrocoder.taskmanager.domain.port.in;

import com.necrocoder.taskmanager.domain.model.Task;

import java.util.UUID;

public interface GetTaskByIdUseCase {
    Task execute(UUID taskId);
}
