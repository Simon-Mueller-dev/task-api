# Task API

A simple RESTful Task Management API built with Java, Jakarta REST (JAX-RS), Jersey and Apache Tomcat.

The project demonstrates the implementation of a CRUD-based REST API including proper HTTP methods, 
status codes and JSON data handling. It is designed as a lightweight backend service and serves as a foundation
for further extensions such as persistence, authentication or CI/CD integration.


## Technology Stack

- **Java 17**
- **Jakarta EE 10**
- **JAX-RS (Jakarta REST)**
- **Jersey 3**
- **Apache Tomcat 10**
- **Maven** (build tool)
- **JSON-Binding (Jersey JSON-Binding)**


## Architecture & Structure

The project follows a simple and clean layered structure:

- **Model**: `Task` entity representing the data structure.
- **Service**: `TaskService` contains business logic and in-memory storage.
- **Resource**: `TaskResource` defines REST endpoints (GET, POST, PUT, DELETE).
- **Configuration**: `RestApplication` configures Jersey and the base API path.

This separation keeps the API clean and makes it easy to replace the in-memory storage with a database in the future.

---

## API Endpoints

### 🔹 GET /api/tasks
Returns all tasks.

**Response**
- Status: `200 OK`
- Body: JSON array of tasks

---

### 🔹 POST /api/tasks
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

### 🔹 PUT /api/tasks/{id}
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

### 🔹 DELETE /api/tasks/{id}
Deletes a task.

**Response**
- Status: `204 No Content` if deleted
- Status: `404 Not Found` if task does not exist

---


## Local Setup

### 🔹 Requirements
- Java 17
- Maven
- Apache Tomcat 10

### 🔹 Build the project
```bash
mvn clean package
```

### 🔹 Deploy the WAR to Tomcat
Copy the generated WAR file from:
```
target/task-api-1.0.0.war
```
to the Tomcat /webapps/ folder.

### 🔹 Access the API
Once Tomcat is running, the API can be accessed at:
```
http://localhost:8080/task-api-1.0.0/api/tasks
```

---

## Example Requests

### 🔹 Get all tasks
```bash
curl http://localhost:8080/task-api-1.0.0/api/tasks
```

### 🔹 Create a new Task
```
curl -X POST http://localhost:8080/task-api-1.0.0/api/tasks \
-H "Content-Type: application/json" \
-d '{"title":"New task","description":"Created via API","completed":false}'
```

### 🔹 Update an existing Task
```
curl -X PUT http://localhost:8080/task-api-1.0.0/api/tasks/1 \
-H "Content-Type: application/json" \
-d '{"title":"Updated task","description":"Updated via API","completed":true}'
```

### 🔹 Delete a Task
```
curl -X DELETE http://localhost:8080/task-api-1.0.0/api/tasks/1
```

**Note:** On Windows (PowerShell or CMD), the curl commands may need to be written in a single line.    

---


## 🚀 CI/CD Pipeline

This project uses **GitHub Actions** to automate building, testing, and security checks.

The pipeline runs on every push and pull request to the `main` branch and consists of the following stages:

---

### 🔹 Build & Test

- The project is built using **Maven**.
- Unit tests are executed automatically to validate core business logic (e.g. `TaskService`).
- The pipeline ensures that only working and tested code is merged.

---

### 🔹 Vulnerability Scanning

- **Trivy Vulnerability Scanner** is used to detect known security vulnerabilities (CVEs) in project dependencies.
- The scan runs automatically as part of the CI pipeline.
- Vulnerabilities are reported for visibility but do not fail the build for this demo project.

**Known finding:**
- A vulnerability in the transitive dependency `org.eclipse.parsson:parsson` (CVE-2023-7272) was detected.
- This issue was reviewed and classified as low risk for this project.

---

### 🔹 License Scanning

- **Trivy License Scanner** is used to analyze open-source licenses of all project dependencies.
- Each dependency is classified into categories such as:
    - **Allowed** – permissive licenses (e.g. MIT, Apache 2.0)
    - **Restricted** – licenses with additional conditions (e.g. GPL, LGPL)
    - **Forbidden** – incompatible licenses
- The scan is executed automatically in the CI pipeline and serves as an early license compliance check.
- Any findings are reviewed and documented where necessary.


### 📜 Dependency Licenses Overview

All project dependencies were scanned using the **Trivy License Scanner**.

A deduplicated list of detected licenses is maintained in:

➡ **[`LICENSES_FOUND.txt`](LICENSES_FOUND.txt)**

This file is generated from local scans and reviewed to ensure license transparency.

Restricted licenses detected during scanning were evaluated and are acceptable for this non-commercial demo project.

---

### 🔹 Benefits

- Automated verification of build, tests, and security.
- Early detection of vulnerabilities and license risks.
- Demonstrates **DevSecOps best practices** in a modern CI/CD workflow.



## Possible Improvements

The project is intentionally kept simple. Possible next steps include:

- Persisting tasks using a database (e.g. PostgreSQL, MySQL) with JPA/Hibernate
- Adding validation and better error handling
- Implementing authentication and authorization
- Writing unit and integration tests
- Adding CI/CD pipelines using GitHub Actions
- Containerization with Docker
