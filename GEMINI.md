# GEMINI.md

## Project Overview

Task API is a containerized RESTful backend service built with Java and Jakarta EE.

The project demonstrates modern backend engineering and DevSecOps practices, including:

- Layered architecture (Resource, Service, Model)
- RESTful CRUD endpoints
- Maven-based build system
- Docker containerization
- CI/CD pipeline with GitHub Actions
- Dependency vulnerability scanning (Trivy)
- License compliance scanning
- Semantic versioning and structured release management

Current stable version: v1.0.0


---

## Technology Stack

- Java 17
- Jakarta EE 10
- JAX-RS (Jersey 3)
- Apache Tomcat 10
- Maven
- Docker
- GitHub Actions
- Trivy (Security & License Scanning)

The application is packaged as a WAR file and deployed on Apache Tomcat.

In the Docker setup, the application is deployed as `ROOT.war`.


---

## Architecture & Structure

The project follows a clear layered architecture:

- **Model** → Domain objects (e.g., `Task`)
- **Service** → Business logic (`TaskService`, in-memory storage)
- **Resource** → REST endpoints (`TaskResource`)
- **Configuration** → Jersey configuration (`RestApplication`)

Currently, the project uses in-memory storage (no database integration).


---

## Coding Guidelines for Gemini

When assisting with this project:

1. Do not modify the overall architecture.
2. Maintain strict separation between layers.
3. Do not introduce unnecessary frameworks.
4. Always use `jakarta.*` namespaces (never `javax.*`).
5. Ensure compatibility with Jakarta EE 10 and Jersey 3.
6. Avoid breaking changes unless explicitly requested.
7. Suggest only stable and compatible Maven dependencies.
8. Do not introduce features outside the defined scope.
9. Keep changes incremental and focused.


## Interaction Rules

- **No Unauthorized Code Changes:** Gemini is not permitted to modify any source code files directly or suggest changes without explicit user approval.
- **Preview Requirement:** Before proposing any modifications, Gemini must provide a detailed preview of the intended changes.
- **Review Process:** For every proposed change, Gemini must explain *why* the change is necessary and show a "diff" or a clear code snippet of the proposed version.
- **Approval Flow:** Implementation may only proceed after the user has reviewed and explicitly confirmed the previewed changes.

---

## Development Workflow

- Changes must be incremental.
- Commit messages must be clear and meaningful.
- The CI pipeline must remain green after changes.
- Security and license scanning must continue to function.

Example commit formats:

feat: add OpenAPI integration  
fix: correct TaskService update logic  
chore: upgrade dependency versions  
docs: improve API documentation


---

## Current Roadmap

Planned improvements:

- OpenAPI 3 integration
- Swagger UI
- Automated GitHub Release workflow
- Platform upgrade (Java 25 / Jakarta EE 11)
- Extended AI-assisted development workflow

Gemini should primarily assist with:

- Code generation
- Configuration support
- Dependency compatibility analysis
- Refactoring suggestions
- Documentation improvements


---

## Constraints

- No database integration at this stage.
- No migration to Spring Boot unless explicitly requested.
- No breaking API changes.
- No experimental or unstable libraries.


---

## Deployment Context

Local Tomcat deployment:
http://localhost:8080/task-api-1.0.0/api/tasks

Docker deployment:
http://localhost:8080/api/tasks


---

## AI Usage Policy

Gemini is used as a coding assistant.

All generated code must be manually reviewed and understood before integration.

Gemini suggestions are advisory, not authoritative.

Final architectural decisions remain the responsibility of the developer.