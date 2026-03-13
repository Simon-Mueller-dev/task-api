package io.github.simonmuellerdev.taskapi.config;

import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource;
import io.swagger.v3.jaxrs2.integration.resources.AcceptHeaderOpenApiResource;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
        title = "Task API",
        version = "1.1.0",
        description = "A simple RESTful Task management API built with Jakarta EE 10"
         )
        )

@ApplicationPath("/api/v1")
public class RestApplication extends ResourceConfig {

    public RestApplication() {
        packages("io.github.simonmuellerdev.taskapi.resource");
        // Register the Swagger/OpenAPI resources
        register(OpenApiResource.class);
        register(AcceptHeaderOpenApiResource.class);

        property("jersey.config.server.wadl.disableWadl", true);
    }
}

