package com.reservationmanagement.transactions.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TransactionNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public TransactionNotFoundException(String message) {
        super(message);
    }
	
}
