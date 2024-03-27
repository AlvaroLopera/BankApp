package com.example.app.service;

import java.util.List;

import com.example.app.dto.TransactionDTO;

public interface TransactionService {
    
    List<TransactionDTO> getAllTransactionsByAccountNumber(String accountNumber);

}
