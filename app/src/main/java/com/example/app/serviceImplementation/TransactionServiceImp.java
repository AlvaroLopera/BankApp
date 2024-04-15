package com.example.app.serviceImplementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.dto.TransactionDTO;
import com.example.app.entity.TransactionEntity;
import com.example.app.mapper.TransactionMapper;
import com.example.app.repository.TransactionRepo;
import com.example.app.service.TransactionService;

@Service
public class TransactionServiceImp implements TransactionService {

	@Autowired
	private TransactionRepo transactionRepository;

	 @Autowired
	    private TransactionMapper transactionMapper;

	 @Override
	 public List<TransactionDTO> getAllTransactionsByAccountNumber(String accountNumber) {
	
        List<TransactionEntity> transactions = transactionRepository.findBySourceAccount_AccountNumberOrTargetAccount_AccountNumber(accountNumber, accountNumber);
	     
	    List<TransactionDTO> transactionDTOs = transactions.stream().map(transactionMapper::toDto)
	             .sorted((t1, t2) -> t2.getTransaction_date().compareTo(t1.getTransaction_date()))
	             .collect(Collectors.toList());

	     return transactionDTOs;
	 } 


}