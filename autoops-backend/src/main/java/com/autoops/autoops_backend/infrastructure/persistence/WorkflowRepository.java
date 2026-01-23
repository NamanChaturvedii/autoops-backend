package com.autoops.autoops_backend.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface WorkflowRepository extends JpaRepository<WorkflowEntity, UUID> {

    List<WorkflowEntity> findByOwnerUserId(String ownerUserId);
}
