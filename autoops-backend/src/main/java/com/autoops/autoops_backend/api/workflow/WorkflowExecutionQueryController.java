package com.autoops.autoops_backend.api.workflow;


import com.autoops.autoops_backend.infrastructure.persistence.WorkflowExecutionEntity;
import com.autoops.autoops_backend.infrastructure.persistence.WorkflowExecutionRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/workflows")
public class WorkflowExecutionQueryController {
    private  final WorkflowExecutionRepository executionRepository;

    public  WorkflowExecutionQueryController(WorkflowExecutionRepository executionRepository){
        this.executionRepository = executionRepository;
    }


    @GetMapping("/{workflowId}/executions")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_OPERATOR','ROLE_VIEWER')")
    public List<WorkflowExecutionEntity> getExecutions(
            @PathVariable UUID workflowId
            ){
        return executionRepository.findByWorkflowIdOrderByStartedAtDesc(workflowId);
    }
}
