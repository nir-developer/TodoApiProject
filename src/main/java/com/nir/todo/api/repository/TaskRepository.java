package com.nir.todo.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nir.todo.api.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
