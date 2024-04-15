package com.example.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.app.entity.AccountEntity;
import com.example.app.entity.TransactionEntity;

@Repository
public interface TransactionRepo extends JpaRepository <TransactionEntity, Long> {
    
    List<TransactionEntity> findBySourceAccount ( AccountEntity source );

    List<TransactionEntity> findByTargetAccount ( AccountEntity target );

    List<TransactionEntity> findBySourceAccount_AccountNumberOrTargetAccount_AccountNumber(String sourceAccountNumber, String targetAccountNumber);

}
