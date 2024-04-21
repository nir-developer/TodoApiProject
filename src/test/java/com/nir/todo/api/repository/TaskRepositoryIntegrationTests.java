package com.nir.todo.api.repository;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import com.nir.todo.api.model.Task;

import jakarta.persistence.EntityManager;


//NOTE - IF ID DONT HAVE ANY DATASOURE IN THE application.properties - then Spring Data will create it by default
//NOTE : THIS WILL OVERRIDE THE PROFILE DEFINED IN THE APPLICATION PROPERTIES?
//@ActiveProfiles("test")


@DataJpaTest
//NONE => SAME DB(DATASOURCE)  AS IN THE APP PROPERTIES WILL BE USED
@AutoConfigureTestDatabase(replace = Replace.NONE)
//FALSE -> MODIFYY THE DB DATA
@Rollback(false)
public class TaskRepositoryIntegrationTests {
	
	
	@Autowired
	private TaskRepository taskRepository; 
	
	@Autowired
	private EntityManager entityManager; 
	
	
	List<Task> tasks; 
	@BeforeEach
	void setup()
	{
			Task task1 = new Task("task1 title", "task 1 description", true);
			Task task2 = new Task("task2 title", "task 2 description", false);
			Task task3 = new Task("task3 title", "task 3 description", false);

			tasks = new ArrayList<>();
			tasks.add(task1);
			tasks.add(task2);
			tasks.add(task3);
			
			
			
			this.entityManager.persist(task1);
			this.entityManager.persist(task2);
			this.entityManager.persist(task3);

			
			//this.taskRepository.saveAll(this.tasks);
			
			System.out.println(">>>>INSIDE BEFOREEACH ENTITYMANAGER PERSISTED 3 TASKS:");
			this.tasks.forEach(t -> System.out.println(t));
		

	}

	
	
	@DisplayName("Test Dependency Injection Of Repo By S")
	@Test
	void testSmoke() 
	{
		assertThat(this.taskRepository).isNotNull();
		
	}
	
	
		//OK
	@DisplayName("Test Create Task")
	@Test
	void testCreateTask() 
	{
		
		Task task = new Task() ; 
		task.setTaskDescription("This is a test task!!");
		task.setCompleted(false);
		task.setTaskTitle("TEST");
		
		
		//WHEN
		Task savedTask = this.taskRepository.save(task); 
		
		
		
		//THEN
		assertThat(task).isEqualTo(savedTask);
		
	
	}
	
	
	@DisplayName("Test Find All Tasks")
	@Test
	void testFindAllTasks()
	{
		List<Task> actual=  this.taskRepository.findAll();
		
		assertThat(actual).isEqualTo(this.tasks);
	}
	
	
	@AfterEach
	void tearDown()
	{
		//this.tasks = null; 
		this.entityManager.createQuery("DELETE FROM Task").executeUpdate();
		
	}
	

}
