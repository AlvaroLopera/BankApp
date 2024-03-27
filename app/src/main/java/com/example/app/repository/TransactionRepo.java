package com.example.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.app.entity.AccountEntity;
import com.example.app.entity.TransactionEntity;

public interface TransactionRepo extends JpaRepository <TransactionEntity, Long> {
    
    List<TransactionEntity> findBySourceAccount ( AccountEntity source );

    List<TransactionEntity> findByTargetAccount ( AccountEntity target );

}
