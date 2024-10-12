package com.reservationmanagement.customer.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import lombok.Data;

@Data
@Entity
public class CustomerAppointment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private long appointmentId;
	@Transient
	private String appointmentDetail;
	
	@JsonIgnore //it is necesary for avoid infinite recursion
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Customer.class)
	@JoinColumn(name = "customerId", nullable = true)
	private Customer customer;
	
}
