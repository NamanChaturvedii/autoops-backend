package com.autoops.autoops_backend.api.metrics;


import com.autoops.autoops_backend.domain.workflow.ExecutionStatus;
import com.autoops.autoops_backend.infrastructure.persistence.WorkflowExecutionRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/metrics")
public class MetricsController {

    private final WorkflowExecutionRepository executionRepository;


    public  MetricsController ( WorkflowExecutionRepository executionRepository){
        this.executionRepository  = executionRepository;
    }


    @GetMapping("/executions/summary")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Map<String , Object> executionSummary (){
        return Map.of(
                "success",executionRepository.countByStatus(ExecutionStatus.SUCCESS),
                "failed",executionRepository.countByStatus(ExecutionStatus.FAILED)
        );
    }

    @GetMapping("/workflows/{workflowId}/executions/count")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_OPERATOR')")
    public  long workFlowExecutionCount(
            @PathVariable UUID workflowId
            ){
        return  executionRepository.countByWorkflowId(workflowId);
    }


}
