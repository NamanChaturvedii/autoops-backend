package com.autoops.autoops_backend.infrastructure.persistence;

import com.autoops.autoops_backend.domain.workflow.ExecutionStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface WorkflowExecutionRepository
        extends JpaRepository<WorkflowExecutionEntity, UUID> {

    List<WorkflowExecutionEntity> findByWorkflowId(UUID workflowId);
    List<WorkflowExecutionEntity>
    findByWorkflowIdOrderByStartedAtDesc(UUID workflowId);


    long countByStatus(ExecutionStatus status);
    long countByWorkflowId(UUID workflowId);
}

