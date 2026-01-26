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

### GET /api/tasks
Returns all tasks.

**Response**
- Status: `200 OK`
- Body: JSON array of tasks

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

### Deploy the WAR to Tomcat
Copy the generated WAR file from:
```
target/task-api-1.0.0.war
```
to the Tomcat /webapps/ folder.

### Access the API
Once Tomcat is running, the API can be accessed at:
```
http://localhost:8080/task-api-1.0.0/api/tasks
```

---

## Example Requests

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

## Possible Improvements

The project is intentionally kept simple. Possible next steps include:

- Persisting tasks using a database (e.g. PostgreSQL, MySQL) with JPA/Hibernate
- Adding validation and better error handling
- Implementing authentication and authorization
- Writing unit and integration tests
- Adding CI/CD pipelines using GitHub Actions
- Containerization with Docker
