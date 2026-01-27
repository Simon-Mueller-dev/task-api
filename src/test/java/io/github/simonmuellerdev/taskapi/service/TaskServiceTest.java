package io.github.simonmuellerdev.taskapi.service;

import io.github.simonmuellerdev.taskapi.model.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskServiceTest {

    @Test
    void shouldCreateTask() {
        TaskService service = new TaskService();

        Task newTask = new Task();
        newTask.setTitle("Test Task");
        newTask.setDescription("Created via test");

        Task task = service.createTask(newTask);

        assertNotNull(task);
        assertEquals("Test Task", task.getTitle());
        assertFalse(task.isCompleted());
    }

}
