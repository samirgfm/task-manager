package com.tasks.manager.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tasks.manager.model.Task;

// Mongo implementation
@Repository
public class TaskRepositoryImpl implements TaskRepository {

    @Autowired
    private MongoTaskRepository mongoTaskRepository;

    @Override
    public List<Task> findAll() {
        return mongoTaskRepository.findAll();
    }

    @Override
    public Optional<Task> findById(String id) {
        return mongoTaskRepository.findById(id);
    }

    @Override
    public Task save(Task task) {
        return mongoTaskRepository.save(task);
    }

    @Override
    public Task updateById(String id, Task task) {
        if (!mongoTaskRepository.existsById(id)) {
            System.out.println("Task not found.");
            return null;
        }
        task.setId(id);
        return mongoTaskRepository.save(task);

    }

    @Override
    public void deleteById(String id) {
        if (!mongoTaskRepository.existsById(id)) {
            System.out.println("Task not found.");
        }
        mongoTaskRepository.deleteById(id);
    }
}