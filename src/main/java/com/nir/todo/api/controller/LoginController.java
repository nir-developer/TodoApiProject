package com.nir.todo.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nir.todo.api.model.Customer;
import com.nir.todo.api.repository.CustomerRepository;

@RestController
@RequestMapping("/todoapp/api/v1")
public class LoginController {
	
	private final CustomerRepository customerRepository;
	
	public LoginController(CustomerRepository customerRepository)
	{
		this.customerRepository = customerRepository;
	}
	
	
	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody Customer customer)
	{
		Customer savedCustomer = null; 
		ResponseEntity  response = null; 
		
		try 
		{
			savedCustomer = customerRepository.save(customer);
			if(savedCustomer.getId() > 0)
			{
				response = ResponseEntity.status(HttpStatus.CREATED)
						.body("Given user details are successfully registered"); 
			}
		}
		catch(Exception exc)
		{
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An exception occured due to" + exc.getMessage());
			
		}
		
		return response; 
	}
	
	
	
	
}
