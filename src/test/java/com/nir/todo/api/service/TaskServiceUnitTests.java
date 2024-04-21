package com.nir.todo.api.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
//import static org.hamcrest.CoreMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.nir.todo.api.model.Task;
import com.nir.todo.api.repository.TaskRepository;

@ExtendWith(MockitoExtension.class)
public class TaskServiceUnitTests {
	
	
	List<Task> tasks;
	
	@Mock 
	private TaskRepository taskRepository; 
	
	
	
	//SUT: INJECT THE MOCK - SINCE @Service does not do this 
	@InjectMocks 
	private TaskService taskService; 
	
	
	
	//DO I HAVE TO CREATE THE SUT AND INJECT THE REPO VIA CONSTRUCTOR INJECTINO??
	@BeforeEach
	void setup()
	{
		
		Task task1 = new Task("task1 title", "task 1 description", true);
		Task task2 = new Task("task2 title", "task 2 description", false);
		Task task3 = new Task("task3 title", "task 3 description", false);

		tasks = new ArrayList<>();
		tasks.addAll(List.of(task1,task2, task3));
		
		
		this.taskRepository.saveAll(this.tasks);
		
	}
	
	@DisplayName("Test Save Task")
	@Test
	void testCreateTask()
	{
		//GIVEN
		Task task = new Task() ; 
		task.setTaskDescription("This is a test task!!");
		task.setCompleted(false);
		task.setTaskTitle("TEST");
		
		//CONFIGURE THE MOCK 
		when(this.taskRepository.save(task)).thenReturn(task); 
		
		
		//when(this.taskRepository.save(any(Task.class))).then(returnFirstArg());
		Task savedTask = this.taskService.save(task); 
		
		assertThat(savedTask).isNotNull();
		
		
		///VERIFY MOCK WAS CALLED WITH THE CORRECT PARAMETER 
		verify(this.taskRepository).save(task);
		System.out.println(savedTask);
		

	}
	
	@DisplayName("Test Find All Tasks")
	@Test
	void testFindAllTasks() 
	{
		
		when(this.taskRepository.findAll()).thenReturn(this.tasks);
		
		
		//MUST CALL THE SUT - OTHERWISSE EXCEPTION OF UN NECCERAY STABBING!! 
		List<Task> actualTasks = this.taskService.findAll(); 
		
		
		assertThat(this.tasks).isEqualTo(actualTasks);
		
		actualTasks.forEach(task -> System.err.println(task));
		
		//VERIFY 
		verify(this.taskRepository).findAll();
		
		
	}

}
