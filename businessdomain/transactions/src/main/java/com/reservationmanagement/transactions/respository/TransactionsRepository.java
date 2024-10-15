package com.reservationmanagement.transactions.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reservationmanagement.transactions.entities.Transaction;
import java.util.List;


public interface TransactionsRepository extends JpaRepository<Transaction, Long> {
	
	public List<Transaction> findByEmailCustomer(String emailCustomer);
	
}
