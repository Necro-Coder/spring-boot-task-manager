package com.necrocoder.taskmanager.application;

import com.necrocoder.taskmanager.application.service.GetTaskByIdService;
import com.necrocoder.taskmanager.domain.model.Task;
import com.necrocoder.taskmanager.domain.model.valueobject.TaskStatus;
import com.necrocoder.taskmanager.domain.port.out.TaskRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetTaskByIdServiceTest {

    private static final UUID taskId = UUID.randomUUID();

    @Mock
    private TaskRepositoryPort taskRepositoryPort;

    @InjectMocks
    private GetTaskByIdService getTaskByIdService;

    Task TASK_PREPARED() {
        return Task.recreate(
                taskId,
                "Test Task",
                "This is a test task",
                LocalDateTime.of(2025, 12, 31, 23, 59),
                "High",
                TaskStatus.PENDING,
                LocalDateTime.now()
        );
    }

    @Test
    public void testGetTaskById() {
        when(taskRepositoryPort.findById(taskId)).thenReturn(Optional.of(TASK_PREPARED()));
        getTaskByIdService.execute(taskId);
    }
}
