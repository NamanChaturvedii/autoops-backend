package com.autoops.autoops_backend.application.audit;


import com.autoops.autoops_backend.infrastructure.persistence.AuditLogEntity;
import com.autoops.autoops_backend.infrastructure.persistence.AuditLogRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class AuditService {

    private  final AuditLogRepository repo;


    public  AuditService(AuditLogRepository repo){
        this.repo  = repo;
    }

    public void log(String actorUserId, String action, String resource) {
        AuditLogEntity log = new AuditLogEntity();
        log.setId(UUID.randomUUID());
        log.setActorUserId(actorUserId);
        log.setAction(action);
        log.setResource(resource);
        log.setCreatedAt(Instant.now());
        repo.save(log);
    }


}
