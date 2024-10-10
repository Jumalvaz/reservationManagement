package com.reservationmanagement.customer.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reservationmanagement.customer.entities.Customer;

/**
 *
 * @author Jumalvaz
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    
}
