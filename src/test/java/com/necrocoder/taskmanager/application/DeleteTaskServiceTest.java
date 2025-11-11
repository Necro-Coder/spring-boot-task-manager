package com.necrocoder.taskmanager.application;

import com.necrocoder.taskmanager.application.service.DeleteTaskService;
import com.necrocoder.taskmanager.domain.model.Task;
import com.necrocoder.taskmanager.domain.model.valueobject.TaskStatus;
import com.necrocoder.taskmanager.domain.port.out.TaskRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DeleteTaskServiceTest {

    private static final UUID TASK_ID = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

    @Mock
    private TaskRepositoryPort taskRepositoryPort;

    @InjectMocks
    private DeleteTaskService deleteTaskService;

    Task TASK_PREPARED() {
        return Task.recreate(
                TASK_ID,
                "Test Task",
                "This is a test task",
                java.time.LocalDateTime.of(2025, 12, 31, 23, 59),
                "High",
                TaskStatus.PENDING,
                java.time.LocalDateTime.now()
        );
    }

    @Test
    public void testDeleteTask() {
        when(taskRepositoryPort.findById(TASK_ID)).thenReturn(Optional.of(TASK_PREPARED()));
        deleteTaskService.execute(TASK_ID);
    }
}
