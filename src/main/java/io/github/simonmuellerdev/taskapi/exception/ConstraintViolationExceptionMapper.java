package io.github.simonmuellerdev.taskapi.exception;

import io.github.simonmuellerdev.taskapi.model.ValidationError;
import io.github.simonmuellerdev.taskapi.model.ValidationErrorResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Provider
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException exception) {
        List<ValidationError> errors = exception.getConstraintViolations().stream()
                .map(violation -> new ValidationError(
                        getFieldName(violation.getPropertyPath()),
                        violation.getMessage()))
                .toList();

        ValidationErrorResponse response = new ValidationErrorResponse(
                Response.Status.BAD_REQUEST.getStatusCode(),
                "Request validation failed",
                errors
        );

        return Response.status(Response.Status.BAD_REQUEST)
                .entity(response)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    private String getFieldName(Path path) {
        // Extracts the actual field name (e.g., "title") from the full path
        return StreamSupport.stream(path.spliterator(), false)
                .reduce((first, second) -> second)
                .map(Path.Node::getName)
                .orElse("unknown");
    }
}
