package com.necrocoder.taskmanager.domain.port.in;

import com.necrocoder.taskmanager.application.dto.CreateTaskCommand;
import com.necrocoder.taskmanager.domain.model.Task;

public interface CreateTaskUseCase {
    Task execute(CreateTaskCommand command);
}
