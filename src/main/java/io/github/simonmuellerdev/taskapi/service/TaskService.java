package io.github.simonmuellerdev.taskapi.service;

import io.github.simonmuellerdev.taskapi.model.Task;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import io.github.simonmuellerdev.taskapi.exception.TaskNotFoundException;

public class TaskService {

    private final Map<Long, Task> tasks = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(0);

    // GET /tasks
    public Collection<Task> getAllTasks() {
        return tasks.values();
    }

    // GET /tasks/{id}
    public Task getTaskById(long id) {

        Task task = tasks.get(id);

        if (task == null) {
            throw new TaskNotFoundException(id);
        }

        return task;
    }
    // POST /tasks
    public Task createTask(Task task) {
        long id = idGenerator.incrementAndGet();
        task.setId(id);
        tasks.put(id, task);
        return task;
    }

    // PUT /tasks/{id}
    public Task updateTask(long id, Task updatedTask) {
        Task existing = tasks.get(id);

        if (existing == null) {
            throw new TaskNotFoundException(id);
        }

        existing.setTitle(updatedTask.getTitle());
        existing.setDescription(updatedTask.getDescription());
        existing.setCompleted(updatedTask.isCompleted());

        return existing;
    }

    // DELETE /tasks/{id}
    public void deleteTask(long id) {

        Task removed = tasks.remove(id);

        if (removed == null) {
            throw new TaskNotFoundException(id);
        }
    }

}
