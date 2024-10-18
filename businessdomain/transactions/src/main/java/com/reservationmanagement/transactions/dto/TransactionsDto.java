package com.reservationmanagement.transactions.dto;

import java.time.Instant;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Schema(hidden = true)
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "set", toBuilder = true)
@EqualsAndHashCode
public class TransactionsDto {

	private Long id;
	
	private String emailCustomer;
	
	private String action;
	
	private Instant date;
}
