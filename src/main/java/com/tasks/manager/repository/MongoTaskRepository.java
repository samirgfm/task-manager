package com.tasks.manager.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tasks.manager.model.Task;

@Repository
public interface MongoTaskRepository extends MongoRepository<Task, String> {
}
