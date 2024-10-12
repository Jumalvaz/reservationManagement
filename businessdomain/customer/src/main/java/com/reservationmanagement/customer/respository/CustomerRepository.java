package com.reservationmanagement.customer.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reservationmanagement.customer.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    
	public Customer findByEmail(String email);
	
}
