package com.nir.todo.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nir.todo.api.model.Task;
import com.nir.todo.api.service.TaskService;

@RestController
@RequestMapping("/todoapp/api/v1/tasks")
public class TaskController {

	
	
	private  final TaskService taskService; 
	
	public TaskController(TaskService taskService)
	{
		this.taskService = taskService;
	}
		
	
	//OK
	@GetMapping
	public List<Task> getAllTasks()
	{
		return this.taskService.findAll();
	
	}
	
	@PostMapping
	public Task createTask(@RequestBody Task task)
	{
		return this.taskService.save(task);
	}
}
