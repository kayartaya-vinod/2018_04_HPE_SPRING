package com.hpe.springboot.training.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.hpe.springboot.training.entity.Customer;

public interface CustomerDao extends CrudRepository<Customer, Integer> {

	public List<Customer> findByCity(String city);
	
	@Query("from Customer where email = ? and password = ?")
	public Customer searchByEmailPassword(String email, String password);
	
	// spring writes query for the property "email"
	public Customer findByEmail(String email);
	
}
