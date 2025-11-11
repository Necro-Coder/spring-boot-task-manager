package com.necrocoder.taskmanager.application;

import com.necrocoder.taskmanager.application.dto.CreateTaskCommand;
import com.necrocoder.taskmanager.application.service.CreateTaskService;
import com.necrocoder.taskmanager.domain.model.Task;
import com.necrocoder.taskmanager.domain.port.out.TaskRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ExtendWith(MockitoExtension.class)
public class CreateTaskServiceTest {

    @Mock
    private TaskRepositoryPort taskRepositoryPort;

    @InjectMocks
    private CreateTaskService createTaskService;

    public Task TASK_PREPARED() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return Task.create(
                "Test Task",
                "This is a test task",
                LocalDateTime.parse("31/12/2025 23:59", dtf),
                "High"
        );
    }

    @Test
    public void testCreateTask() {
        Task task = TASK_PREPARED();
        createTaskService.execute(new CreateTaskCommand(
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                task.getPriority()
        ));
    }
}
