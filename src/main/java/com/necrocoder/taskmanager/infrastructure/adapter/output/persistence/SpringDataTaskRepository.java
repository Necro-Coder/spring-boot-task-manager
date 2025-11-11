package com.necrocoder.taskmanager.infrastructure.adapter.output.persistence;

import com.necrocoder.taskmanager.infrastructure.persistence.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataTaskRepository extends JpaRepository<TaskEntity, UUID> {

}
