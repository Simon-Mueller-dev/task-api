package io.github.simonmuellerdev.taskapi.resource;

import io.github.simonmuellerdev.taskapi.model.Task;
import io.github.simonmuellerdev.taskapi.service.TaskService;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/tasks")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TaskResource {

    private static final TaskService taskService = new TaskService();

    @GET
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @POST
    public Response createTask(Task task) {
        Task createdTask = taskService.addTask(task);
        return Response.status(Response.Status.CREATED)
                .entity(createdTask)
                .build();
    }
}
