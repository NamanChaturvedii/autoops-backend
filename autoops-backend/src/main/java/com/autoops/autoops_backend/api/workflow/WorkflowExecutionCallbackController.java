package com.autoops.autoops_backend.api.workflow;


import com.autoops.autoops_backend.domain.workflow.ExecutionStatus;
import com.autoops.autoops_backend.infrastructure.persistence.WorkflowExecutionEntity;
import com.autoops.autoops_backend.infrastructure.persistence.WorkflowExecutionRepository;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.UUID;

@RestController
@RequestMapping("internal/executions")
public class WorkflowExecutionCallbackController {

    private  final WorkflowExecutionRepository executionRepository;

    public  WorkflowExecutionCallbackController(
            WorkflowExecutionRepository executionRepository
    ){
        this.executionRepository = executionRepository;
    }

    @PostMapping("/{executionId}/success")
    public void markSuccess(@PathVariable UUID executionId){
        WorkflowExecutionEntity execution =
                executionRepository.findById(executionId).orElseThrow();

        execution.setStatus(ExecutionStatus.SUCCESS);
        execution.setFinishedAt(Instant.now());
        executionRepository.save(execution);
    }

    @PostMapping("/{executionId}/failure")
    public  void markFailure(
            @PathVariable UUID executionId,
            @RequestBody String errorMessage
    ) {
        WorkflowExecutionEntity execution =
                executionRepository.findById(executionId).orElseThrow();

        execution.setStatus(ExecutionStatus.FAILED);
        execution.setFinishedAt(Instant.now());
        execution.setErrorMessage(errorMessage);
        executionRepository.save(execution);
    }

}
