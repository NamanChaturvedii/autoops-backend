package com.autoops.autoops_backend.api.workflow;


import com.autoops.autoops_backend.application.audit.AuditService;
import com.autoops.autoops_backend.domain.workflow.ExecutionStatus;
import com.autoops.autoops_backend.infrastructure.n8n.N8nClient;
import com.autoops.autoops_backend.infrastructure.persistence.WorkflowEntity;
import com.autoops.autoops_backend.infrastructure.persistence.WorkflowExecutionEntity;
import com.autoops.autoops_backend.infrastructure.persistence.WorkflowExecutionRepository;
import com.autoops.autoops_backend.infrastructure.persistence.WorkflowRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/workflows")
public class WorkflowController {

    private  final WorkflowRepository workflowRepository;

    private final WorkflowExecutionRepository executionRepository;

    private  final AuditService auditService;

    private  final N8nClient n8nClient;



    public WorkflowController(WorkflowRepository workflowRepository,AuditService auditService,N8nClient n8nClient,
                              WorkflowExecutionRepository executionRepository){
        this.workflowRepository = workflowRepository;
        this.auditService = auditService;
        this.n8nClient = n8nClient;
        this.executionRepository= executionRepository;
    }


    @PostMapping
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_OPERATOR')")
    public WorkflowEntity createWorkflow(
            @RequestBody WorkflowEntity workflow,
            Authentication authentication
    ){
        workflow.setId(UUID.randomUUID());
        workflow.setOwnerUserId(authentication.getName());
        workflow.setStatus("CREATED");
        workflow.setCreatedAt(Instant.now());
        workflow.setUpdatedAt(Instant.now());

        WorkflowEntity saved = workflowRepository.save(workflow);

        auditService.log(
                authentication.getName(),
                "CREATE_WORKFLOW",
                saved.getName()
        );

        WorkflowExecutionEntity execution = new WorkflowExecutionEntity();
        execution.setId(UUID.randomUUID());
        execution.setWorkflowId(saved.getId());
        execution.setStatus(ExecutionStatus.PENDING);
        execution.setStartedAt(Instant.now());

        executionRepository.save(execution);


        n8nClient.triggerWorkflow(
                saved.getId().toString(),
                execution.getId().toString()
        );


        return saved;


    }


    @GetMapping
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_OPERATOR','ROLE_VIEWER')")
    public List<WorkflowEntity> listWorkflows(Authentication authentication) {
        return workflowRepository.findByOwnerUserId(authentication.getName());
    }

}
