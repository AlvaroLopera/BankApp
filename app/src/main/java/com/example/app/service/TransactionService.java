package com.example.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.app.dto.TransactionDTO;

@Service
public interface TransactionService {
    
    List<TransactionDTO> getAllTransactionsByAccountNumber(String accountNumber);

}
