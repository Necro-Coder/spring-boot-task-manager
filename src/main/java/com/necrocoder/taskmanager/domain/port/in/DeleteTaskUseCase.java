package com.necrocoder.taskmanager.domain.port.in;

import java.util.UUID;

public interface DeleteTaskUseCase {
    void execute(UUID taskId);
}
