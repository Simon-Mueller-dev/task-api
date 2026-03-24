package io.github.simonmuellerdev.taskapi.service;

import io.github.simonmuellerdev.taskapi.exception.TaskNotFoundException;
import io.github.simonmuellerdev.taskapi.model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskServiceTest {

    private TaskService taskService;

    @BeforeEach
    void setUp() {
        taskService = new TaskService();
    }

    @Test
    void shouldCreateTask() {
        Task task = new Task();
        task.setTitle("Test Task");
        task.setDescription("Description");

        Task created = taskService.createTask(task);

        assertTrue(created.getId() > 0);
        assertEquals("Test Task", created.getTitle());
        assertEquals("Description", created.getDescription());
        assertFalse(created.isCompleted());
    }

    @Test
    void shouldReturnTaskById() {
        Task task = new Task();
        task.setTitle("Find me");

        Task created = taskService.createTask(task);

        Task found = taskService.getTaskById(created.getId());

        assertEquals(created.getId(), found.getId());
        assertEquals("Find me", found.getTitle());
    }

    @Test
    void shouldThrowExceptionWhenTaskNotFound() {
        assertThrows(TaskNotFoundException.class, () -> {
            taskService.getTaskById(999L);
        });
    }

    @Test
    void shouldUpdateExistingTask() {
        Task task = new Task();
        task.setTitle("Old Title");
        task.setDescription("Old Description");

        Task created = taskService.createTask(task);

        Task update = new Task();
        update.setTitle("New Title");
        update.setDescription("New Description");
        update.setCompleted(true);

        Task updated = taskService.updateTask(created.getId(), update);

        assertEquals("New Title", updated.getTitle());
        assertEquals("New Description", updated.getDescription());
        assertTrue(updated.isCompleted());
    }

    @Test
    void shouldThrowExceptionWhenUpdatingNonExistentTask() {
        Task update = new Task();
        update.setTitle("Test");

        assertThrows(TaskNotFoundException.class, () -> {
            taskService.updateTask(999L, update);
        });
    }

    @Test
    void shouldDeleteTask() {
        Task task = new Task();
        task.setTitle("Delete me");

        Task created = taskService.createTask(task);

        taskService.deleteTask(created.getId());

        assertThrows(TaskNotFoundException.class, () -> {
            taskService.getTaskById(created.getId());
        });
    }

    @Test
    void shouldThrowExceptionWhenDeletingNonExistentTask() {
        assertThrows(TaskNotFoundException.class, () -> {
            taskService.deleteTask(999L);
        });
    }
}