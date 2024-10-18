package com.reservationmanagement.transactions.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reservationmanagement.transactions.entities.TransactionsEntity;
import java.util.List;


public interface TransactionsRepository extends JpaRepository<TransactionsEntity, Long> {
	
	public List<TransactionsEntity> findByEmailCustomer(String emailCustomer);
	
}
