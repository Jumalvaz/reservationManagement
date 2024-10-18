package com.reservationmanagement.transactions.entities;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class TransactionsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private long id;
	
	@Column(name = "EMAIL_CUSTOMER")
	private String emailCustomer;
	
	@Column(name = "ACTION")
	private String action;
	
	@Column(name = "DATE")
	private Instant date;

}
