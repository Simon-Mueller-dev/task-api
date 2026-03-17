package io.github.simonmuellerdev.taskapi.exception;

import io.github.simonmuellerdev.taskapi.model.ErrorResponse;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.logging.Level;
import java.util.logging.Logger;

@Provider
public class GlobalExceptionMapper implements ExceptionMapper<Throwable> {
    private static final Logger LOGGER = Logger.getLogger(GlobalExceptionMapper.class.getName());

    @Override
    public Response toResponse(Throwable exception) {
        int status = Response.Status.INTERNAL_SERVER_ERROR.getStatusCode();
        String message = exception.getMessage() != null
                ? exception.getMessage()
                : "Unexpected error occurred";

        if (exception instanceof WebApplicationException wae) {
            status = wae.getResponse().getStatus();
        } else {
            LOGGER.log(Level.SEVERE, "Unexpected error occurred", exception);
            message = "An internal server error occurred.";
        }

        ErrorResponse errorResponse = new ErrorResponse(status, message);
        return Response.status(status)
                .entity(errorResponse)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
