package com.nir.todo.api.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.nir.todo.api.model.Customer;

import jakarta.persistence.EntityManager;

@DataJpaTest
//NONE => SAME DB(DATASOURCE)  AS IN THE APP PROPERTIES WILL BE USED
@AutoConfigureTestDatabase(replace = Replace.NONE)
//FALSE -> MODIFYY THE DB DATA
@Rollback(false)
public class CustomerRepositoryIntegrationTests {
	
	@Autowired
	private EntityManager entityManager; 
	
	@Autowired
	private CustomerRepository customerRepository; 
	
	@DisplayName("Create Customer")
	@Test
	void testCreateCustomer()
	{
		Customer customer = new Customer("test@gmail.com", "superduper100", "ADMIN") ; 
		
		Customer savedCustomer = this.customerRepository.save(customer);
		
		
		assertThat(savedCustomer).isEqualTo(savedCustomer);
		
		System.out.println("NEW CUSTOMER: "); 
		System.out.println(savedCustomer);
	}
	
	@DisplayName("Find Customer By Email When Found")
	@Test
	void testFindCustomerByEmailWhenCustomerExists()
	{
		String testEmail = "test@gmail.com"; 
		
		Customer customer = this.customerRepository.findByEmail(testEmail).get(0);
		
		assertThat(customer).isNotNull();
		
		System.out.println(customer);
		
	}
	
	@DisplayName("Find Customer By Email When Not Found")
	@Test
	void testFindCustomerByEmailWhenCustomerNotExists()
	{
		String testEmail = "test@gmailllll.com"; 
		
		List<Customer> customers = this.customerRepository.findByEmail(testEmail);
		
		assertThat(customers.size()).isEqualTo(0);
		
		System.out.println(customers);
		
	}

	

}
