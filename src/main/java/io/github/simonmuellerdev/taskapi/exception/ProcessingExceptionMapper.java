package io.github.simonmuellerdev.taskapi.exception;

import io.github.simonmuellerdev.taskapi.model.ErrorResponse;
import jakarta.json.bind.JsonbException;
import jakarta.ws.rs.ProcessingException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ProcessingExceptionMapper implements ExceptionMapper<ProcessingException> {

    @Override
    public Response toResponse(ProcessingException exception) {

        Throwable cause = exception.getCause();

        // Prüfen ob es wirklich ein JSON Fehler ist
        if (cause instanceof JsonbException) {

            ErrorResponse error = new ErrorResponse(
                    Response.Status.BAD_REQUEST.getStatusCode(),
                    "Invalid JSON format"
            );

            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(error)
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }

        // Wenn kein JSON Fehler → weiterreichen (wichtig!)
        throw exception;
    }
}
