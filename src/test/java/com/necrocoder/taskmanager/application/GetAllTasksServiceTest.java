package com.necrocoder.taskmanager.application;

import com.necrocoder.taskmanager.application.service.GetAllTasksService;
import com.necrocoder.taskmanager.domain.model.Task;
import com.necrocoder.taskmanager.domain.model.valueobject.TaskStatus;
import com.necrocoder.taskmanager.domain.port.out.TaskRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetAllTasksServiceTest {

    @Mock
    private TaskRepositoryPort taskRepositoryPort;

    @InjectMocks
    private GetAllTasksService getAllTasksService;

    List<Task> TASKS_PREPARED() {
        return List.of(
                Task.recreate(
                        java.util.UUID.fromString("123e4567-e89b-12d3-a456-426614174000"),
                        "Test Task 1",
                        "This is the first test task",
                        java.time.LocalDateTime.of(2025, 12, 31, 23, 59),
                        "High",
                        TaskStatus.PENDING,
                        java.time.LocalDateTime.now()
                ),
                Task.recreate(
                        java.util.UUID.fromString("223e4567-e89b-12d3-a456-426614174001"),
                        "Test Task 2",
                        "This is the second test task",
                        java.time.LocalDateTime.of(2025, 11, 30, 22, 0),
                        "Medium",
                        TaskStatus.COMPLETED,
                        java.time.LocalDateTime.now()
                )
        );
    }

    @Test
    public void testGetAllTasks() {
        when(taskRepositoryPort.findAll()).thenReturn(TASKS_PREPARED());
        getAllTasksService.execute();
    }

}
