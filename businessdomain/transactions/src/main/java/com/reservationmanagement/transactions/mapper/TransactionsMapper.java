package com.reservationmanagement.transactions.mapper;

import static java.util.stream.Collectors.toList;

import java.util.Collection;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.reservationmanagement.transactions.dto.TransactionsDto;
import com.reservationmanagement.transactions.dto.TransactionsDto.TransactionsDtoBuilder;
import com.reservationmanagement.transactions.entities.TransactionsEntity;

@Component
public class TransactionsMapper {

	private final ModelMapper mapper;
	
	public TransactionsMapper(ModelMapper mapper) {
		this.mapper = mapper;
	}
	
	public TransactionsDto mapToBusinessObjects(TransactionsEntity entity) {
		TransactionsDto transactions = this.mapper.map(entity, TransactionsDtoBuilder.class).build();
		return transactions;
	}
	
	public List<TransactionsDto> mapToBusinessObjects(Collection<TransactionsEntity> entities){
		return entities.stream().map(this::mapToBusinessObjects).collect(toList());
	}
	
	public TransactionsEntity mapToDataObject(TransactionsDto transaction) {
		TransactionsEntity entity = this.mapper.map(transaction, TransactionsEntity.class);
		return entity;
	}
	
}
