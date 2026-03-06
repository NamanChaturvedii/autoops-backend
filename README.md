AutoOps AI – Backend

AutoOps AI is a workflow orchestration and automation control platform that integrates Spring Boot, PostgreSQL, JWT security, and n8n automation workflows to allow users to trigger, monitor, and analyze operational automations from a centralized dashboard.

This repository contains the backend service responsible for authentication, workflow orchestration, execution tracking, security, and system observability.

Architecture Overview

AutoOps separates workflow orchestration from workflow execution.

Components

Spring Boot Backend
Control plane responsible for security, APIs, and execution tracking.

n8n Workflow Engine
Executes automation workflows.

PostgreSQL Database
Stores workflows, executions, and metrics.

Frontend (React Native Web)
Dashboard to trigger workflows and view results.

System Flow
Frontend (React Native Web)
        |
        v
Spring Boot Backend
(Authentication + RBAC + APIs)
        |
        v
n8n Workflow Engine
(automation execution)
        |
        v
PostgreSQL
(execution tracking & metrics)
Key Features
Authentication & Security

JWT Authentication

Role Based Access Control (RBAC)

Supported roles:

ADMIN

OPERATOR

USER

Workflow Management

Register workflows created in n8n

Trigger workflows from UI

Maintain workflow metadata

Execution Tracking

Each execution follows a lifecycle:

PENDING → SUCCESS
PENDING → FAILED

Execution data stored includes:

executionId

workflowId

status

triggeredBy

timestamps

failure message

Automation Integration

Backend triggers n8n via webhook.

POST /webhook/autoops/execute

n8n sends callback to backend:

/internal/executions/{executionId}/success
/internal/executions/{executionId}/failure
Metrics & Observability

Dashboard metrics include:

Success executions

Failed executions

Success rate

Execution history

Tech Stack
Backend

Java 21

Spring Boot

Spring Security

JWT Authentication

PostgreSQL

Maven

Automation

n8n (self-hosted)

Frontend

React Native Web

Infrastructure

Docker (planned deployment)

Project Structure
src/main/java/com/autoops

api/
Controllers exposing REST endpoints

application/
Business logic and orchestration

domain/
Core entities and enums

infrastructure/
External integrations
 - n8n client
 - persistence
 - security

config/
Application configuration
API Overview
Authentication

Login

POST /auth/login

Returns JWT token.

Workflows

Create workflow

POST /workflows

Trigger workflow execution

POST /workflows/{workflowId}/execute

Get execution history

GET /workflows/{workflowId}/executions
Metrics

Execution summary

GET /metrics/executions/summary
Workflow Execution Flow

User logs into AutoOps dashboard.

User selects a workflow and clicks Run.

Backend creates execution record with PENDING status.

Backend triggers n8n webhook.

n8n executes automation workflow.

n8n sends callback to backend.

Backend updates execution status.

Frontend dashboard updates metrics.

Local Development
Prerequisites

Java 21

Maven

PostgreSQL

n8n (self hosted)

Run backend
mvn spring-boot:run

Backend runs at

http://localhost:8080

n8n webhook endpoint

http://localhost:5678/webhook/autoops/execute
Future Improvements

Admin UI for user management

Workflow templates

Advanced analytics

Alerting integrations (Slack / Email)

Docker deployment

CI/CD pipelines

License

MIT License
