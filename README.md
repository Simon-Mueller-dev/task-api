# Task API

A containerized RESTful API for managing tasks, built with Java, Jakarta EE, Maven, and Apache Tomcat.

This project demonstrates modern backend engineering practices, including CI/CD automation, dependency vulnerability scanning, license compliance analysis, and Docker-based deployment.

## Features

- RESTful CRUD API
- Layered architecture (Resource, Service, Model)
- Unit testing for business logic
- Maven-based build system
- Docker containerization
- CI/CD pipeline with GitHub Actions
- Automated vulnerability scanning (Trivy)
- Automated license compliance scanning


## Technology Stack

- **Java 17**
- **Jakarta EE 10**
- **JAX-RS (Jakarta REST)**
- **Jersey 3** (JAX-RS implementation)
- **Apache Tomcat 10** (Servlet container)
- **Maven** (build tool)
- **JSON-B (Jersey JSON-Binding)**
- **Docker**
- **GitHub Actions**

## Architecture & Structure

The project follows a clean, layered architecture:

- **Model**: `Task` entity representing the domain object.
- **Service**: `TaskService` contains business logic and in-memory data storage.
- **Resource**: `TaskResource` defines REST endpoints (GET, POST, PUT, DELETE).
- **Configuration**: `RestApplication` configures Jersey and defining the base API path.

This separation ensures maintainability and allows future replacement of the in-memory storage with a persistent database without affecting the API layer.

The application is packaged as a WAR file and deployed on Apache Tomcat.

---

## API Endpoints

### GET /api/tasks
Returns all tasks.

**Response**
- Status: `200 OK`
- Body: JSON array of task objects

---

### POST /api/tasks
Creates a new task.

**Response**
- Status: `201 Created`
- Body: JSON object with task data

Example request body:
```json
{
  "title": "New task",
  "description": "Task description",
  "completed": false
}
```
---

### PUT /api/tasks/{id}
Updates an existing task.

**Response**
- Status: `200 OK` if updated
- Status: `404 Not Found` if task does not exist

Example request body:
```json
{
  "title": "Updated task",
  "description": "Updated description",
  "completed": true
}
```
---

### DELETE /api/tasks/{id}
Deletes a task.

**Response**
- Status: `204 No Content` if deleted
- Status: `404 Not Found` if task does not exist

---


## Local Setup

### Requirements
- Java 17
- Maven
- Apache Tomcat 10

### Build the project
```bash
mvn clean package
```

### Deploy to Tomcat
Copy the generated WAR file from:

`target/task-api-1.0.0.war`

to the Tomcat `webapps/` directory and start Tomcat.

### Access the API
When deployed directly as a WAR file, the application is accessible at:
```
http://localhost:8080/task-api-1.0.0/api/tasks
```
When running via `Docker` (deployed as ROOT.war), the API is available at:
```
http://localhost:8080/api/tasks
```

---

## Example Requests
The following examples use `curl` to interact with the API.

### Get all tasks
```bash
curl http://localhost:8080/task-api-1.0.0/api/tasks
```

### Create a new Task
```
curl -X POST http://localhost:8080/task-api-1.0.0/api/tasks \
-H "Content-Type: application/json" \
-d '{"title":"New task","description":"Created via API","completed":false}'
```

### Update an existing Task
```
curl -X PUT http://localhost:8080/task-api-1.0.0/api/tasks/1 \
-H "Content-Type: application/json" \
-d '{"title":"Updated task","description":"Updated via API","completed":true}'
```

### Delete a Task
```
curl -X DELETE http://localhost:8080/task-api-1.0.0/api/tasks/1
```

**Note:** On Windows (PowerShell or CMD), the curl commands may need to be written in a single line.    

---

## Run with Docker

The API can be executed inside a Docker container without requiring a local Java or Tomcat installation.

### Build the application

First, package the WAR file:

```bash
mvn clean package
```

### Build the Docker image

```bash
docker build -t task-api .
```

### Run the container

```bash
docker run -p 8080:8080 task-api
```

The application is deployed as `ROOT.war`, therefore the API is available at:

```
http://localhost:8080/api/tasks
```

---

## CI/CD Pipeline

The project uses GitHub Actions for continuous integration and security automation.

The pipeline runs on every push and pull request and performs:

- Maven build
- Unit test execution
- Dependency vulnerability scanning (Trivy)
- License compliance scanning (Trivy)

This setup demonstrates DevSecOps practices by integrating automated security checks directly into the development workflow.

---

### Build & Test

- The project is built using **Maven**.
- Unit tests are executed automatically to validate core business logic (e.g. `TaskService`).
- The pipeline ensures that only working and tested code is merged.

---

### Vulnerability Scanning

- **Trivy Vulnerability Scanner** is used to detect known security vulnerabilities (CVEs) in project dependencies.
- The scan runs automatically as part of the CI pipeline.
- Vulnerabilities are reported for visibility but do not fail the build for this demo project.

**Known finding:**
- A vulnerability in the transitive dependency `org.eclipse.parsson:parsson` (CVE-2023-7272) was detected.
- This issue was reviewed and classified as low risk for this project.

---

### License Scanning

- **Trivy License Scanner** is used to analyze open-source licenses of all project dependencies.
- Each dependency is classified into categories such as:
    - **Allowed** – permissive licenses (e.g. MIT, Apache 2.0)
    - **Restricted** – licenses with additional conditions (e.g. GPL, LGPL)
    - **Forbidden** – incompatible licenses
- The scan is executed automatically in the CI pipeline and serves as an early license compliance check.
- Any findings are reviewed and documented where necessary.

---

### AI-Assisted Development

This project was developed with the support of an AI coding assistant.

AI was used to explore implementation approaches, validate design decisions, troubleshoot configuration issues, and refine CI/CD and Docker setup.

All architectural decisions and implementation details were designed, reviewed, and validated by the developer.

AI suggestions were treated as guidance rather than authority, ensuring full understanding and ownership of the final solution.


### AI Usage Principles

- AI suggestions were treated as guidance, not authority
- All code was reviewed and understood before integration
- Security and license decisions were made manually

---

### Dependency Licenses Overview

All project dependencies were scanned using the **Trivy** License Scanner.

A deduplicated list of detected licenses is maintained in:

➡ **[`LICENSES_FOUND.txt`](LICENSES_FOUND.txt)**

Restricted licenses detected during scanning were reviewed and are acceptable for this non-commercial demo project.

---

### Benefits

- Automated verification of build, tests, and security.
- Early detection of vulnerabilities and license risks.
- Demonstrates **DevSecOps best practices** in a modern CI/CD workflow.



## Possible Improvements

The project is intentionally kept simple. Potential next steps include:

- Persisting tasks using a database (e.g., PostgreSQL) with JPA/Hibernate
- Adding request validation and improved error handling
- Implementing authentication and authorization (JWT)
- Expanding integration test coverage
- Adding OpenAPI/Swagger documentation
- Publishing the Docker image to a container registry
- Extending the CI pipeline to build and scan the Docker image
