package com.necrocoder.taskmanager.application;

import com.necrocoder.taskmanager.application.dto.UpdateTaskCommand;
import com.necrocoder.taskmanager.application.service.UpdateTaskService;
import com.necrocoder.taskmanager.domain.model.Task;
import com.necrocoder.taskmanager.domain.model.valueobject.TaskStatus;
import com.necrocoder.taskmanager.domain.port.out.TaskRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UpdateTaskServiceTest {
    private final static UUID TASK_ID = UUID.randomUUID();

    @Mock
    private TaskRepositoryPort taskRepositoryPort;

    @InjectMocks
    private UpdateTaskService updateTaskService;

    Task TASK_PREPARED() {
        return Task.recreate(
                TASK_ID,
                "To Be Updated Test Task",
                "This is a test task to be updated",
                java.time.LocalDateTime.of(2025, 12, 31, 23, 59),
                "Low",
                TaskStatus.PENDING,
                java.time.LocalDateTime.now()
        );
    }

    UpdateTaskCommand TASK_SAVE() {
        return new UpdateTaskCommand(
                "Updated Test Task",
                "This is an updated test task",
                java.time.LocalDateTime.of(2026, 1, 31, 23, 59),
                "High",
                TaskStatus.COMPLETED.name()
        );
    }

    @Test
    public void testUpdateTask() {
        when(taskRepositoryPort.findById(TASK_ID)).thenReturn(java.util.Optional.of(TASK_PREPARED()));

        updateTaskService.execute(TASK_ID, TASK_SAVE());
    }


}
