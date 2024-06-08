package com.tasks.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.tasks.manager.model.Task;
import com.tasks.manager.model.TaskStatus;
import com.tasks.manager.repository.TaskRepository;
import com.tasks.manager.service.TaskService;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    private Task task;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        task = new Task();
        task.setId("task1ID");
        task.setTitle("New Task");
        task.setDescription("New task description");
        task.setDueDate(LocalDate.of(2024, 6, 8));
        task.setTags(Arrays.asList("label1", "label2"));
        task.setStatus(TaskStatus.PENDING);
    }

    @Test
    void createTask() {
        when(taskRepository.save(task)).thenReturn(task);

        Task createdTask = taskService.createTask(task);

        assertEquals(task, createdTask);
        verify(taskRepository, times(1)).save(task);
    }

    @Test
    void getAllTasks() {
        List<Task> tasks = Arrays.asList(task);
        when(taskRepository.findAll()).thenReturn(tasks);

        List<Task> fetchedTasks = taskService.getAllTasks();

        assertEquals(tasks, fetchedTasks);
        verify(taskRepository, times(1)).findAll();
    }

    @Test
    void deleteTask() {
        doNothing().when(taskRepository).deleteById("task1ID");

        taskService.deleteTask("task1ID");

        verify(taskRepository, times(1)).deleteById("task1ID");
    }

}