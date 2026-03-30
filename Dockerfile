# Use official Tomcat image with Java 25
FROM tomcat:10.1-jdk25-temurin

# Remove default Tomcat webapps (clean container)
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy built WAR into Tomcat ROOT context
COPY target/task-api-1.2.0.war /usr/local/tomcat/webapps/ROOT.war

# Document container port
EXPOSE 8080

# Run Tomcat in foreground (required for Docker)
CMD ["catalina.sh", "run"]