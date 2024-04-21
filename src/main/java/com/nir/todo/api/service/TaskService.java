package com.nir.todo.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nir.todo.api.model.Task;
import com.nir.todo.api.repository.TaskRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TaskService {
	

	private final TaskRepository taskRepository;

	public TaskService(TaskRepository taskRepository) {
	
		this.taskRepository = taskRepository;
	}
	
	
	public List<Task> findAll() 
	{
		return this.taskRepository.findAll();
	}


	public Task save(Task task) {
		
		return this.taskRepository.save(task);
	}
	
	
	
	
	

}
