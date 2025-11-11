package com.necrocoder.taskmanager.domain.port.in;

import com.necrocoder.taskmanager.application.dto.UpdateTaskCommand;
import com.necrocoder.taskmanager.domain.model.Task;

import java.util.UUID;

public interface UpdateTaskUseCase {
    Task execute(UUID taskIde, UpdateTaskCommand updateTaskCommand);
}
