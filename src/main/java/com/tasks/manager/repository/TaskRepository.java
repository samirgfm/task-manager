package com.tasks.manager.repository;

import java.util.List;
import java.util.Optional;

import com.tasks.manager.model.Task;

public interface TaskRepository {
    List<Task> findAll();

    Optional<Task> findById(String id);

    Task save(Task task);

    Task updateById(String id, Task task);

    void deleteById(String id);
}
