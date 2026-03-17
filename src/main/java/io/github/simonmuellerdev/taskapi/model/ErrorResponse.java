package io.github.simonmuellerdev.taskapi.model;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.OffsetDateTime;

@Schema(description = "Standard API error response")
public class ErrorResponse {
    @Schema(description = "HTTP Status Code", example = "404")
    private int status;

    @Schema(description = "Error message", example = "Task not found with ID: 123")
    private String message;

    @Schema(description = "Timestamp of the error")
    private OffsetDateTime timestamp;

    public ErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = OffsetDateTime.now();
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public OffsetDateTime getTimestamp() {
        return timestamp;
    }
}
