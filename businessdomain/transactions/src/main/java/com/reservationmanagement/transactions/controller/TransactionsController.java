package com.reservationmanagement.transactions.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.reservationmanagement.transactions.dto.TransactionsDto;
import com.reservationmanagement.transactions.service.TransactionsService;

@RestController
@RequestMapping("/transactions")
public class TransactionsController {

	private final TransactionsService service;
	
	public TransactionsController(TransactionsService service) {
		this.service = service;
	}
	
	
	@GetMapping()
	public ResponseEntity<List<TransactionsDto>> getAll() {
		List<TransactionsDto> transactions = service.findAllTransactions();
		
		if(transactions.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(transactions, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TransactionsDto> get(@PathVariable("id") long id) {
		TransactionsDto transaction = service.findTransactionById(id);
	    
		return new ResponseEntity<>(transaction, HttpStatus.OK);
	}
	
	@GetMapping("/customer/transactions")
	public ResponseEntity<List<TransactionsDto>> get(@RequestParam("email") String email){
		List<TransactionsDto> transactions = service.findByEmailCustomer(email);
		
		if(transactions.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(transactions, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<TransactionsDto> updateTransaction(@PathVariable("id") long id, @RequestBody TransactionsDto input) {
		TransactionsDto transaction = service.updateTransaction(id, input);
		
		return new ResponseEntity<>(transaction, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<TransactionsDto> createTransaction(@RequestBody TransactionsDto input) {
		TransactionsDto saveTransaction = service.createTransaction(input);
		
		return new ResponseEntity<>(saveTransaction, HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteTransaction(@PathVariable("id") long id) {
		service.deleteTransaction(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
