package com.reservationmanagement.transactions.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.reservationmanagement.transactions.entities.Transaction;
import com.reservationmanagement.transactions.respository.TransactionsRepository;

@RestController
@RequestMapping("/transactions")
public class TransactionsRestController {

	@Autowired
	TransactionsRepository transactionsRepository;
	
	
	@GetMapping()
	public List<Transaction> list() {
		return transactionsRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> get(@PathVariable("id") long id) {
		Optional<Transaction> transaction = transactionsRepository.findById(id);
		if (transaction.isPresent()) {
			return new ResponseEntity<>(transaction.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/customer/transactions")
	public List<Transaction> get(@RequestParam("email") String email){
		return transactionsRepository.findByEmailCustomer(email);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> put(@PathVariable("id") long id, @RequestBody Transaction input) {
		Optional<Transaction> optionalTransaction = transactionsRepository.findById(id);
		if (optionalTransaction.isPresent()) {
			Transaction newTransaction = optionalTransaction.get();
			newTransaction.setEmailCustomer(input.getEmailCustomer());
			newTransaction.setAction(input.getAction());
			newTransaction.setDate(input.getDate());
			Transaction save = transactionsRepository.save(newTransaction);
			return new ResponseEntity<>(save, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping
	public ResponseEntity<?> post(@RequestBody Transaction input) {
		Transaction save = transactionsRepository.save(input);
		return ResponseEntity.ok(save);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") long id) {
		Optional<Transaction> optionalTransaction = transactionsRepository.findById(id);
		if(optionalTransaction.isPresent()) {
			transactionsRepository.delete(optionalTransaction.get());
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
