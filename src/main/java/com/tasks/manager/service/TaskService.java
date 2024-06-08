package com.tasks.manager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tasks.manager.model.Task;
import com.tasks.manager.model.TaskStatus;
import com.tasks.manager.repository.TaskRepository;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Optional<Task> getTask(String id) {
        return taskRepository.findById(id);
    }

    public Task createTask(Task task) {
        task.setStatus(TaskStatus.PENDING);
        return taskRepository.save(task);
    }

    public Task updateTask(String id, Task task) {
        return taskRepository.updateById(id, task);
    }

    public void deleteTask(String id) {
        taskRepository.deleteById(id);
    }

}