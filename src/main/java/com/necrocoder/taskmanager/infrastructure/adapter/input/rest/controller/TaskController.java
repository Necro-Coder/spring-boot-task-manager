package com.necrocoder.taskmanager.infrastructure.adapter.input.rest.controller;

import com.necrocoder.taskmanager.application.dto.CreateTaskCommand;
import com.necrocoder.taskmanager.application.dto.UpdateTaskCommand;
import com.necrocoder.taskmanager.domain.model.Task;
import com.necrocoder.taskmanager.domain.port.in.*;
import com.necrocoder.taskmanager.infrastructure.adapter.input.rest.dto.CreateTaskRequest;
import com.necrocoder.taskmanager.infrastructure.adapter.input.rest.dto.TaskResponse;
import com.necrocoder.taskmanager.infrastructure.adapter.input.rest.dto.UpdateTaskRequest;
import com.necrocoder.taskmanager.infrastructure.adapter.input.rest.mapper.TaskRestMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final CreateTaskUseCase createTaskUseCase;
    private final UpdateTaskUseCase updateTaskUseCase;
    private final GetTaskByIdUseCase getTaskByIdUseCase;
    private final GetAllTasksUseCase getAllTasksUseCase;
    private final DeleteTaskUseCase deleteTaskUseCase;

    public TaskController(CreateTaskUseCase createTaskUseCase,
                          UpdateTaskUseCase updateTaskUseCase,
                          GetTaskByIdUseCase getTaskByIdUseCase,
                          GetAllTasksUseCase getAllTasksUseCase,
                          DeleteTaskUseCase deleteTaskUseCase) {
        this.createTaskUseCase = createTaskUseCase;
        this.updateTaskUseCase = updateTaskUseCase;
        this.getTaskByIdUseCase = getTaskByIdUseCase;
        this.getAllTasksUseCase = getAllTasksUseCase;
        this.deleteTaskUseCase = deleteTaskUseCase;
    }

    @PostMapping
    public ResponseEntity<TaskResponse> createTask(@RequestBody CreateTaskRequest createTaskRequest) {
        CreateTaskCommand createTaskCommand = TaskRestMapper.toCreateCommand(createTaskRequest);

        Task task = createTaskUseCase.execute(createTaskCommand);
        return ResponseEntity.status(201).body(TaskResponse.from(task));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getTaskById(@PathVariable UUID id) {
        Task task = getTaskByIdUseCase.execute(id);
        return ResponseEntity.ok(TaskResponse.from(task));
    }

    @GetMapping
    public ResponseEntity<List<TaskResponse>> getAllTasks() {
        List<Task> tasks = getAllTasksUseCase.execute();
        List<TaskResponse> taskResponses = tasks.stream()
                .map(TaskResponse::from).toList();

        return ResponseEntity.ok(taskResponses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateTask(@PathVariable UUID id, @RequestBody UpdateTaskRequest updateTaskRequest) {
        UpdateTaskCommand updateTaskCommand = TaskRestMapper.toUpdateCommand(updateTaskRequest);
        updateTaskUseCase.execute(id, updateTaskCommand);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable UUID id) {
        deleteTaskUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}
