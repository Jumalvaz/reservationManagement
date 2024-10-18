package com.reservationmanagement.transactions.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reservationmanagement.transactions.Exceptions.TransactionNotFoundException;
import com.reservationmanagement.transactions.dto.TransactionsDto;
import com.reservationmanagement.transactions.entities.TransactionsEntity;
import com.reservationmanagement.transactions.mapper.TransactionsMapper;
import com.reservationmanagement.transactions.respository.TransactionsRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class TransactionsService {
	
	private final TransactionsRepository repository;
	private final TransactionsMapper mapper;
	
	public TransactionsService(TransactionsRepository repository, TransactionsMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}
	
	
	@Transactional
	public List<TransactionsDto> findAllTransactions(){
		log.info("Searching all transactions");
		
		List<TransactionsEntity> transactionsEntity = repository.findAll();
		
		return transactionsEntity.stream()
				.map(mapper::mapToBusinessObjects)
				.collect(Collectors.toList());
	}
	
	@Transactional
	public TransactionsDto findTransactionById(long id) {
		log.info("Searching transaction by id: {}", id);
		
		Optional<TransactionsEntity> optionalTransaction = repository.findById(id);
		
		return optionalTransaction
				.map(mapper::mapToBusinessObjects)
				.orElseThrow(() -> new TransactionNotFoundException("Transaction not found with id: " + id));
	}
	
	@Transactional
	public List<TransactionsDto> findByEmailCustomer(String email){
		log.info("Searching transaction by email: {}", email);
		
		List<TransactionsEntity> transactionsEntity = repository.findByEmailCustomer(email);
		
		return transactionsEntity.stream()
				.map(mapper::mapToBusinessObjects)
				.collect(Collectors.toList());
	}
	
	@Transactional
	public TransactionsDto updateTransaction(long id, TransactionsDto updatedTransaction) {
		log.info("Updating transaction with id: {}", id);
		
		Optional<TransactionsEntity> optionalTransaction = repository.findById(id);
		
		if(optionalTransaction.isPresent()) {
			TransactionsEntity transactionsEntity = optionalTransaction.get();
			transactionsEntity.setEmailCustomer(updatedTransaction.getEmailCustomer());
			transactionsEntity.setAction(updatedTransaction.getAction());
			transactionsEntity.setDate(updatedTransaction.getDate());
			TransactionsEntity savedTransaction = repository.save(transactionsEntity);
			
			return mapper.mapToBusinessObjects(savedTransaction);
		} else {
			throw new TransactionNotFoundException("Transaction not found with id: " + id);
		}
	}
	
	@Transactional
	public TransactionsDto createTransaction(TransactionsDto transaction) {
		log.info("Creating new transaction: {}", transaction);
		
		TransactionsEntity transactionEntity = mapper.mapToDataObject(transaction);
		
		TransactionsEntity savedEntity = repository.save(transactionEntity);
		
		return mapper.mapToBusinessObjects(savedEntity);
	}
	
	@Transactional
	public void deleteTransaction(long id) {
		log.info("Deleting transaction by id: {}", id);
		
		if(repository.existsById(id)) {
			repository.deleteById(id);
		} else {
			throw new TransactionNotFoundException("Transaction not found with id: " + id);
		}
	}

}
