package io.github.simonmuellerdev.taskapi.resource;

import io.github.simonmuellerdev.taskapi.model.Task;
import io.github.simonmuellerdev.taskapi.service.TaskService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.util.Collection;

@Path("/tasks")
@Tag(name = "Tasks", description = "Operations related to task management")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TaskResource {

    private static final TaskService taskService = new TaskService();
    // GET /api/tasks
    @GET
    @Operation(
            summary = "Get all tasks",
            description = "Returns a list of all tasks"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "List of tasks",
                    content = @Content(
                            schema = @Schema(implementation = Task.class)
                    )
            )
    })
    public Collection<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    // GET /api/tasks/{id}
    @GET
    @Path("/{id}")
    @Operation(summary = "Get task by ID", description = "Returns a single task by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task found",
            content = @Content(schema = @Schema(implementation = Task.class))),
            @ApiResponse(responseCode = "404", description = "Task not found")
    })
    public Response getTaskById(@PathParam("id") long id) {
    Task task = taskService.getTaskById(id);
    if (task == null) {
        return Response.status(Response.Status.NOT_FOUND).build();
        }
    return Response.ok(task).build();
    }

    // POST /api/tasks
    @POST
    @Operation(summary = "Create a new task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Task created",
            content = @Content(schema = @Schema(implementation = Task.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public Response createTask(@Valid Task task, @Context UriInfo uriInfo) {
        Task createdTask = taskService.createTask(task);

        // Build the URI for the new resource: /api/tasks/{id}
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(Long.toString(createdTask.getId()));

        return Response.created(builder.build())
            .entity(createdTask)
            .build();
    }

    // PUT /api/tasks/{id}
    @PUT
    @Path("/{id}")
    @Operation(
            summary = "Update an existing task",
            description = "Updates the title or description of an existing task"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task updated successfully"),
            @ApiResponse(responseCode = "404", description = "Task not found")
    })
    public Response updateTask(@PathParam("id") long id, @Valid Task task) {
        Task updated = taskService.updateTask(id, task);

        if (updated == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(updated).build();
    }

    @DELETE
    @Path("/{id}")
    @Operation(
            summary = "Delete a task",
            description = "Deletes a task by its ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Task deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Task not found")
    })
    public Response deleteTask(@PathParam("id") long id) {
        boolean deleted = taskService.deleteTask(id);

        if (!deleted) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.noContent().build();
    }

}
