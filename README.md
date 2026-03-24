# 📝 Task Management API

A containerized RESTful Task Management API built with **Java 17**, **Jakarta EE 10**, and **JAX-RS (Jersey)**.

This project demonstrates modern backend engineering and DevSecOps practices, including:

* OpenAPI 3.0 documentation with Swagger UI
* Structured global error handling
* Bean Validation for request validation
* CI/CD automation with security scanning
* Docker-based deployment

---

# 🚀 Features

* RESTful CRUD API
* OpenAPI 3.0 with Swagger UI
* Global error handling with structured JSON responses
* Field-level validation (Bean Validation, JSR-380)
* JSON error handling (malformed requests)
* Clean layered architecture (Resource, Service, Model, Exception)
* Unit testing for business logic
* Docker containerization
* CI/CD pipeline (GitHub Actions)
* Vulnerability scanning (Trivy)
* License compliance analysis

---

# 🛠️ Tech Stack

* **Java 17**
* **Jakarta EE 10**

  * JAX-RS (Jersey)
  * JSON-B
  * Bean Validation
* **Apache Tomcat 10**
* **Maven**
* **Swagger / OpenAPI 3.0**
* **Docker**
* **GitHub Actions**

---

# 📂 Architecture & Structure

The project follows a clean, layered architecture:

* **Model** → Domain objects (`Task`, `ErrorResponse`, `ValidationError`)
* **Service** → Business logic (`TaskService`)
* **Resource** → REST endpoints (`TaskResource`)
* **Exception** → Centralized error handling via ExceptionMappers
* **Configuration** → `RestApplication` for Jersey & OpenAPI setup

This separation ensures maintainability and allows replacing the in-memory storage with a database without affecting the API layer.

---

# 📖 API Documentation (Swagger UI)

Interactive API documentation is available:

### ▶️ Swagger UI

```text
http://localhost:8080/docs/index.html
```

### 📄 OpenAPI Specification

* JSON: `/api/v1/openapi.json`
* YAML: `/api/v1/openapi.yaml`

---

# 📌 API Endpoints

| Method | Endpoint             | Description       |
| ------ | -------------------- | ----------------- |
| GET    | `/api/v1/tasks`      | Get all tasks     |
| GET    | `/api/v1/tasks/{id}` | Get task by ID    |
| POST   | `/api/v1/tasks`      | Create a new task |
| PUT    | `/api/v1/tasks/{id}` | Update a task     |
| DELETE | `/api/v1/tasks/{id}` | Delete a task     |

---

# ⚠️ Error Handling

The API provides consistent, structured JSON error responses.

## 🔹 Standard Error Response

```json
{
  "status": 404,
  "message": "Task not found",
  "timestamp": "2026-03-17T12:00:00Z"
}
```

---

## 🔹 Validation Error Response

```json
{
  "status": 400,
  "message": "Validation failed",
  "timestamp": "2026-03-17T12:00:00Z",
  "errors": [
    {
      "field": "title",
      "message": "must not be blank"
    }
  ]
}
```

---

## 🔹 Invalid JSON Request

```json
{
  "status": 400,
  "message": "Invalid JSON format",
  "timestamp": "2026-03-17T12:00:00Z"
}
```

---

## 🔹 Internal Server Error

```json
{
  "status": 500,
  "message": "An internal server error occurred",
  "timestamp": "2026-03-17T12:00:00Z"
}
```

---

## 🧠 Error Handling Architecture

| Mapper                             | Responsibility                 |
| ---------------------------------- | ------------------------------ |
| GlobalExceptionMapper              | Fallback for unexpected errors |
| ConstraintViolationExceptionMapper | Bean Validation errors         |
| ProcessingExceptionMapper          | Malformed JSON requests        |
| TaskNotFoundExceptionMapper        | Domain-specific errors         |

---

# 🧪 Example Requests

> 💡 **Tip:**  
> The following examples use `curl` for demonstration purposes.  
> For a more interactive experience, you can explore and test all endpoints via Swagger UI:  
> http://localhost:8080/docs/index.html

### Get all tasks

```bash
curl http://localhost:8080/api/v1/tasks
```

---

### Create a task

```bash
curl -X POST http://localhost:8080/api/v1/tasks \
-H "Content-Type: application/json" \
-d '{"title":"New task","description":"Created via API","completed":false}'
```

---

### Update a task

```bash
curl -X PUT http://localhost:8080/api/v1/tasks/1 \
-H "Content-Type: application/json" \
-d '{"title":"Updated task","description":"Updated","completed":true}'
```

---

### Delete a task

```bash
curl -X DELETE http://localhost:8080/api/v1/tasks/1
```

---

# ▶️ Local Setup

## Requirements

* Java 17
* Maven
* Apache Tomcat 10

---

## Build the project

```bash
mvn clean package
```

---

## Deploy to Tomcat

Copy the WAR file:

```text
target/task-api-1.0.0.war
```

to:

```text
Tomcat /webapps/
```

---

## Access the API

When deployed as WAR:

```text
http://localhost:8080/api/v1/tasks
```

---

# 🐳 Run with Docker

The API can run inside a container without local Java/Tomcat setup.

## Build image

```bash
docker build -t task-api .
```

---

## Run container

```bash
docker run -p 8080:8080 task-api
```

---

## Access API (Docker)

```text
http://localhost:8080/api/v1/tasks
```

---

# ⚙️ CI/CD Pipeline

The project uses GitHub Actions.

Runs on every push:

* Maven build
* Unit tests
* Dependency vulnerability scanning (Trivy)
* License compliance scanning (Trivy)

---

# 🔐 Security & License Scanning

* Detects known CVEs in dependencies
* Analyzes open-source licenses
* Results are reviewed manually

Example finding:

* `org.eclipse.parsson:parsson` (CVE-2023-7272)
  → evaluated as low risk for this project

---

# 🧪 Running Tests

```bash
mvn test
```

---

# 🧠 AI-Assisted Development

AI was used as a development assistant throughout this project.

In particular, the **Gemini CLI** was used as a coding agent directly within the development workflow to:

- explore implementation approaches
- assist with API design and error handling strategies
- debug configuration and runtime issues
- refine architecture and best practices

The AI was integrated as a **tool within the development process**, not as a replacement for understanding.

All generated suggestions were:

- critically reviewed
- manually validated
- adapted to fit the project architecture

This ensured full ownership, correctness, and maintainability of the final implementation.

---

# 🚀 Future Improvements

* Database integration (JPA + PostgreSQL/H2)
* Pagination & filtering
* Authentication (JWT)
* Integration tests (Jersey Test Framework)
* Logging with request correlation IDs
* Docker image publishing

---


# 📜 License

This project is licensed under the MIT License.
