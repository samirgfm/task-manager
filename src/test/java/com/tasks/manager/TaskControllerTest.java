package com.tasks.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.tasks.manager.controller.TaskController;
import com.tasks.manager.model.Task;
import com.tasks.manager.model.TaskStatus;
import com.tasks.manager.service.TaskService;

public class TaskControllerTest {

    @Mock
    private TaskService taskService;

    @InjectMocks
    private TaskController taskController;

    private MockMvc mockMvc;

    private Task task;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(taskController).build();

        task = new Task();
        task.setId("1");
        task.setTitle("New task");
        task.setDescription("New task description");
        task.setDueDate(LocalDate.of(2024, 6, 8));
        task.setTags(Arrays.asList("label1", "label2"));
        task.setStatus(TaskStatus.PENDING);
    }

    @Test
    void createTask() throws Exception {
        when(taskService.createTask(any(Task.class))).thenReturn(task);

        mockMvc.perform(post("/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                        "{\"title\": \"New task\", \"description\": \"New task description\", \"dueDate\": \"2024-06-30\", \"tags\": [\"label1\", \"label2\"]}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("New task"))
                .andExpect(jsonPath("$.description").value("New task description"))
                .andExpect(jsonPath("$.tags[0]").value("label1"))
                .andExpect(jsonPath("$.tags[1]").value("label2"));

        verify(taskService, times(1)).createTask(any(Task.class));
    }

    @Test
    void getAllTasks() throws Exception {
        List<Task> tasks = Arrays.asList(task);
        when(taskService.getAllTasks()).thenReturn(tasks);

        mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("New task"))
                .andExpect(jsonPath("$[0].description").value("New task description"))
                .andExpect(jsonPath("$[0].tags[0]").value("label1"))
                .andExpect(jsonPath("$[0].tags[1]").value("label2"));

        verify(taskService, times(1)).getAllTasks();
    }

    @Test
    void deleteTask() throws Exception {
        doNothing().when(taskService).deleteTask("1");

        mockMvc.perform(delete("/tasks/1"))
                .andExpect(status().isOk());

        verify(taskService, times(1)).deleteTask("1");
    }

}