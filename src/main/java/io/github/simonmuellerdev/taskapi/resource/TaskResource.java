package io.github.simonmuellerdev.taskapi.resource;

import io.github.simonmuellerdev.taskapi.model.Task;
import io.github.simonmuellerdev.taskapi.service.TaskService;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.Collection;

@Path("/tasks")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TaskResource {

    private static final TaskService taskService = new TaskService();

    // GET /api/tasks
    @GET
    public Collection<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    // POST /api/tasks
    @POST
    public Response createTask(Task task) {
        Task createdTask = taskService.createTask(task);
        return Response.status(Response.Status.CREATED)
                .entity(createdTask)
                .build();
    }

    // PUT /api/tasks/{id}
    @PUT
    @Path("/{id}")
    public Response updateTask(@PathParam("id") long id, Task task) {
        Task updated = taskService.updateTask(id, task);

        if (updated == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(updated).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteTask(@PathParam("id") long id) {
        boolean deleted = taskService.deleteTask(id);

        if (!deleted) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.noContent().build();
    }

}
