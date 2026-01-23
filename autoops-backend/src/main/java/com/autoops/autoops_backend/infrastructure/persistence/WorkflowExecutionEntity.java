package com.autoops.autoops_backend.infrastructure.persistence;

import com.autoops.autoops_backend.domain.workflow.ExecutionStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;


@Getter
@Setter
@Entity
@Table(name = "workflow_executions")
public class WorkflowExecutionEntity {

    @Id
    private UUID id;

    @Column(nullable = false)
    private UUID workflowId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ExecutionStatus status;

    @Column(nullable = false)
    private Instant startedAt;

    private Instant finishedAt;

    @Column(length = 1000)
    private String errorMessage;

}
