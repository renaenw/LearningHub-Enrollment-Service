# LearningHub — Enrollment Service

**SFWE 410: Cloud Native Development**  
**Service Owner:** Renae Nicole Weiss

## Overview

The Enrollment Service is part of the LearningHub Learning Management System (LMS). It manages student enrollments and information related to courses, academic terms, and grade levels.

This service was developed using Spring Boot, Java 21, PostgreSQL, and REST APIs. It is designed to operate independently and later integrate with the other LearningHub microservices through an API Gateway.

## GitHub Repository

https://github.com/renaenw/LearningHub-Enrollment-Service/tree/renae-enrollment-service

## Requirements

Before running the application, make sure you have:

- Docker Desktop installed and running
- Git
- Postman for API testing

Java 21 and Maven are used by the Docker build process, so they do not need to be installed separately when using Docker.

## How to Deploy and Run

### 1. Clone the Repository

Open PowerShell and run:

```powershell
git clone -b renae-enrollment-service https://github.com/renaenw/LearningHub-Enrollment-Service.git
cd LearningHub-Enrollment-Service
```

### 2. Start the Enrollment Service

Make sure Docker Desktop is running, then execute:

```powershell
docker compose up -d --build enrollment-service
```

Docker Compose starts the required services:

- PostgreSQL — database storage
- Config Server — application configuration
- Enrollment Service — REST API on port 8104

The first build may take several minutes.

### 3. Verify the Application

Check the containers:

```powershell
docker compose ps
```

Verify the Enrollment Service health endpoint:

```powershell
curl.exe http://localhost:8104/actuator/health
```

A successful response should show:

```json
{
  "status": "UP"
}
```

This confirms that the application is running.

## Enrollment Service API

**Base URL:** `http://localhost:8104/api/enrollment`

The service includes functionality for:

- Student enrollments
- Courses
- Academic terms
- Grade levels

The application uses controllers, services, repositories, and database entities to process and store information.

## Security and Authentication

Spring Security and OAuth2 Resource Server dependencies are included.

The service is configured to validate JWT bearer tokens and use Keycloak realm roles for authorization.

Current security rules include:

- Administrators can create, update, and delete courses and terms.
- Student or administrator roles can create enrollments.
- Enrollment updates and deletions require administrator permissions.
- Other API requests require authentication.
- The health endpoint is publicly accessible.

**Important:** Keycloak is not included in this repository's Docker Compose configuration. To test authenticated API requests, a compatible Keycloak instance must be configured and reachable. Full authentication integration with the shared LearningHub application is still pending verification.

## Testing with Postman

1. Open Postman.
2. Open the Enrollment Service workspace or import the collection.
3. Set the base URL to `http://localhost:8104`.
4. Select the request you want to test.
5. For protected endpoints, configure Authorization as **Bearer Token** and provide a valid JWT from the configured Keycloak realm.
6. Send the request and review the response.

The Postman workspace is provided separately as part of the submission.

**Postman Workspace:** [Add your workspace link here]

## Stopping the Application

To stop the Docker containers, run:

```powershell
docker compose down
```

This stops the services without deleting the PostgreSQL Docker volume.

## Integration with LearningHub

The Enrollment Service is designed to connect with the larger LearningHub system.

In the integrated architecture, requests will pass through the API Gateway to the Enrollment Service. Keycloak will provide authentication, Eureka will support service discovery, and PostgreSQL will store enrollment-related data.

The individual service has been developed separately, with integration into the shared application planned as the next step.
