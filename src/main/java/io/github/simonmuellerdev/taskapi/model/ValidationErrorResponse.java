package io.github.simonmuellerdev.taskapi.model;

import java.util.List;

public class ValidationErrorResponse extends ErrorResponse {
    private List<ValidationError> errors;

    public ValidationErrorResponse(int status, String message, List<ValidationError> errors) {
        super(status, message);
        this.errors = errors;
    }

    public List<ValidationError> getErrors() {
        return errors;
    }
}
