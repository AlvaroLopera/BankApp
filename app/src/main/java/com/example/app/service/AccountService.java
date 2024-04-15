package com.example.app.service;

import org.springframework.stereotype.Service;

import com.example.app.entity.AccountEntity;
import com.example.app.entity.UserEntity;

@Service
public interface AccountService {
    
    public AccountEntity createAccount(UserEntity user);
	public boolean isPinCreated(String accountNumber) ;
	public void createPIN(String accountNumber, String password, String pin) ;
	public void updatePIN(String accountNumber, String oldPIN, String password, String newPIN);
	public void cashDeposit(String accountNumber, String pin, double amount);
	public void cashWithdrawal(String accountNumber, String pin, double amount);
	public void fundTransfer(String sourceAccountNumber, String targetAccountNumber, String pin, double amount);

}
