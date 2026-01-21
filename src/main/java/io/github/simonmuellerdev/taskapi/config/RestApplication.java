package io.github.simonmuellerdev.taskapi.config;

import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/api")
public class RestApplication extends ResourceConfig {

    public RestApplication() {
        packages("io.github.simonmuellerdev.taskapi.resource");
    }
}

