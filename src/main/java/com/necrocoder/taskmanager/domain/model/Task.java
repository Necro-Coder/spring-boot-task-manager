package com.necrocoder.taskmanager.domain.model;

import com.necrocoder.taskmanager.domain.exception.InvalidTaskException;
import com.necrocoder.taskmanager.domain.model.valueobject.TaskStatus;
import com.necrocoder.taskmanager.infrastructure.persistence.TaskEntity;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class Task {
    // title, description, dueDate, priority, status
    private final UUID taskId;
    private String title;
    private String description;
    private LocalDateTime dueDate;
    private String priority;
    private TaskStatus status;
    private LocalDateTime createdAt;

    private Task(UUID taskId, String title, String description, LocalDateTime dueDate, String priority) {
        this.taskId = taskId;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
        this.status = TaskStatus.PENDING;
        this.createdAt = LocalDateTime.now();
    }

    public static Task from(TaskEntity taskEntity) {
        Task task = new Task(
                taskEntity.getId(),
                taskEntity.getTitle(),
                taskEntity.getDescription(),
                taskEntity.getDueDate(),
                taskEntity.getPriority()
        );
        task.createdAt = taskEntity.getCreatedAt();
        task.status = taskEntity.getStatus();
        return task;
    }
    public static Task create(String title, String description, LocalDateTime dueDate, String priority) {
        // business validation
        if (title == null || title.isEmpty()) {
            throw new InvalidTaskException("Title cannot be null or empty");
        }


        return new Task(null, title, description, dueDate, priority);
    }

    public static Task recreate(UUID taskId, String title, String description, LocalDateTime dueDate, String priority, TaskStatus status, LocalDateTime createdAt) {
        Task task = new Task(taskId, title, description, dueDate, priority);
        task.status = status;
        task.createdAt = createdAt;
        return task;
    }

    private void setTitle(String title) {
        this.title = title;
    }

    private void setDescription(String description) {
        this.description = description;
    }

    private void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    private void setPriority(String priority) {
        this.priority = priority;
    }

    public void updateTitle(String title) {
        if(title == null || title.trim().isEmpty()) {
            throw new InvalidTaskException("Title cannot be null or empty");
        }

        if(title.length() > 100) {
            throw new InvalidTaskException("Title cannot be longer than 100 characters");
        }
        setTitle(title);
    }

    public void updateDescription(String description) {
        setDescription(description);
    }

    public void updateDueDate(LocalDateTime dueDate) {
        if(dueDate == this.dueDate) {
            return;
        }

        if(dueDate != null && dueDate.isBefore(LocalDateTime.now())) {
            throw new InvalidTaskException("Due date cannot be before the current date");
        }

        setDueDate(dueDate);
    }

    public void updatePriority(String priority) {
        setPriority(priority);
    }

    public void complete() {
        this.status = TaskStatus.COMPLETED;
    }

    public void cancel() {
        this.status = TaskStatus.CANCELLED;
    }

    public void reopen() {
        this.status = TaskStatus.PENDING;
    }

    public void inProgress() {
        this.status = TaskStatus.IN_PROGRESS;
    }
}
