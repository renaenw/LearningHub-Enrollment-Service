# LearningHub - Enrollment Service

**Renae Nicole Weiss**

## Overview

The enrollment-service is my microservice for Phase 1 of the LearningHub project. 
It handles the enrollment side of the application, including terms, grade levels, 
courses, and student enrollments.

- **Port:** 8104
- **Database:** enrollmentdb
- **Base Path:** `/api/enrollment`

## Main Entities

The service contains four main entities:

- **Term** - Stores information about each academic term.
- **GradeLevel** - Stores the grade levels used for courses.
- **Course** - Stores course information including the term, grade level, teacher, 
  meeting days/times, and credits.
- **Enrollment** - Connects a student username to a course and stores their 
  enrollment status.

Each entity has its own repository, service, and controller for accessing and 
managing its data.

## API Endpoints

### Terms
- `GET /api/enrollment/terms`
- `GET /api/enrollment/terms/{id}`
- `GET /api/enrollment/terms/current`
- `POST /api/enrollment/terms`
- `PUT /api/enrollment/terms/{id}`
- `DELETE /api/enrollment/terms/{id}`

### Grade Levels
- `GET /api/enrollment/grade-levels`
- `GET /api/enrollment/grade-levels/{id}`
- `POST /api/enrollment/grade-levels`
- `PUT /api/enrollment/grade-levels/{id}`
- `DELETE /api/enrollment/grade-levels/{id}`

### Courses
- `GET /api/enrollment/courses`
- `GET /api/enrollment/courses/{id}`
- `POST /api/enrollment/courses`
- `PUT /api/enrollment/courses/{id}`
- `DELETE /api/enrollment/courses/{id}`
- `GET /api/enrollment/courses/term/{termId}`
- `GET /api/enrollment/courses/grade-level/{gradeLevel}`
- `GET /api/enrollment/courses/teacher/{username}`

### Enrollments
- `GET /api/enrollment`
- `GET /api/enrollment/{id}`
- `POST /api/enrollment`
- `PUT /api/enrollment/{id}`
- `DELETE /api/enrollment/{id}`
- `GET /api/enrollment/student/{username}`
- `GET /api/enrollment/course/{courseId}`
- `DELETE /api/enrollment/course/{courseId}/student/{username}`

## Running the Service

The service runs through Docker Compose along with PostgreSQL and the config-service.

For the development profile in PowerShell:

    $env:PROFILE="dev"
    docker compose up -d --build enrollment-service

The service can then be accessed at:

    http://localhost:8104

Health check:

    http://localhost:8104/actuator/health

The active configuration can also be checked at:

    http://localhost:8104/api/enrollment/whoami

The service has both DEV and PROD configuration profiles provided through the 
Spring Cloud Config Server.

## Testing

I tested the service using Postman with requests for all four entities. This 
included creating, viewing, and updating data along with the additional course 
and enrollment search endpoints. I also tested the health check and DEV/PROD 
configuration to make sure the service was connecting to the config server and 
PostgreSQL correctly.

For Phase 1, the enrollment-service can run and be tested independently. 
Integration with the other LearningHub microservices will be handled as the 
project is connected together in later phases.