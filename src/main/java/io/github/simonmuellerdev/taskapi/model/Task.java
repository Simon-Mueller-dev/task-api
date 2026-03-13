package io.github.simonmuellerdev.taskapi.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Task entity representing a todo item")
public class Task {

    @Schema(description = "Unique identifier of the task", example = "1")
    private long id;

    @Schema(description = "Title of the task", example = "Finish OpenAPI integration")
    @NotBlank(message = "Title is mandatory")
    @Size(min = 2, max = 100, message = "Title must be between 2 and 100 characters")
    private String title;

    @Schema(description = "Detailed description of the task", example = "Integrate Swagger UI into the API")
    private String description;

    @Schema(description = "Indicates whether the task is completed", example = "false")
    private boolean completed;

    public Task() {
        // Default-Konstruktor für JSON
    }

    public Task(long id, String title, String description, boolean completed) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.completed = completed;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
