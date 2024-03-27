package com.example.app.serviceImplementation;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.app.entity.AccountEntity;
import com.example.app.entity.TransactionEntity;
import com.example.app.entity.TransactionTypeEntity;
import com.example.app.entity.UserEntity;
import com.example.app.exceptions.InsufficientBalanceException;
import com.example.app.exceptions.NotFoundException;
import com.example.app.exceptions.UnauthorizedException;
import com.example.app.repository.AccountRepo;
import com.example.app.repository.TransactionRepo;
import com.example.app.service.AccountService;

@Service
public class AccountServiceImp implements AccountService {

    @Autowired
    private  AccountRepo accountRep;
	
	@Autowired
    private TransactionRepo transactionRep;
	
	@Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public AccountEntity createAccount(UserEntity user) {
       
        String accountNumber = generateUniqueAccountNumber();
        AccountEntity acc = new AccountEntity();
        acc.setAccountNumber(accountNumber);
        acc.setBalance(0.0);
        return accountRep.save(acc);
    }

    @Override
    public boolean isPinCreated(String accountNumber) {

        AccountEntity account = accountRep.findAccountByNumber( accountNumber );
        
        if (account == null) {
            throw new NotFoundException("Account not found");
        }

        return account.getPin()!=null;
    }
    
	private String generateUniqueAccountNumber() {
	    String accountNumber;
	    do {
	        // Generate a UUID as the account number
	        accountNumber = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 6);
	    } while (accountRep.findAccountByNumber(accountNumber) != null);

	    return accountNumber;
	}

    
    
    @Override
    public void createPIN(String accountNumber, String password, String pin) {
        AccountEntity account = accountRep.findAccountByNumber(accountNumber);
        if (account == null) {
            throw new NotFoundException("Account not found");
        }

        if (!passwordEncoder.matches(password, account.getUser().getPassword())) {
            throw new UnauthorizedException("Invalid password");
        }

        account.setPin(passwordEncoder.encode(pin));
        accountRep.save(account);
    }
    
    @Override
    public void updatePIN(String accountNumber, String oldPIN, String password, String newPIN) {
    	System.out.println(accountNumber+"  "+oldPIN+" "+newPIN+"  "+password);
    	
        AccountEntity account = accountRep.findAccountByNumber(accountNumber);
        if (account == null) {
            throw new NotFoundException("Account not found");
        }

        if (!passwordEncoder.matches(oldPIN, account.getPin())) {
            throw new UnauthorizedException("Invalid PIN");
        }

        if (!passwordEncoder.matches(password, account.getUser().getPassword())) {
            throw new UnauthorizedException("Invalid password");
        }

        account.setPin(passwordEncoder.encode(newPIN));
        accountRep.save(account);
    }
    
    @Override
    public void cashDeposit(String accountNumber, String pin, double amount) {
        AccountEntity account = accountRep.findAccountByNumber(accountNumber);
        if (account == null) {
            throw new NotFoundException("Account not found");
        }

        if (!passwordEncoder.matches(pin, account.getPin())) {
            throw new UnauthorizedException("Invalid PIN");
        }

        double currentBalance = account.getBalance();
        double newBalance = currentBalance + amount;
        account.setBalance(newBalance);
        accountRep.save(account);

        TransactionEntity transaction = new TransactionEntity();
        transaction.setAmount(amount);
        transaction.setTransactionType(TransactionTypeEntity.CASH_DEPOSIT);;
        transaction.setTransaction_date(new Date());
        transaction.setSourceAccount(account);
        transactionRep.save(transaction);
    }
    
    @Override
    public void cashWithdrawal(String accountNumber, String pin, double amount) {
    	
        AccountEntity account = accountRep.findAccountByNumber(accountNumber);
        if (account == null) {
            throw new NotFoundException("Account not found");
        }

        if (!passwordEncoder.matches(pin, account.getPin())) {
            throw new UnauthorizedException("Invalid PIN");
        }

        double currentBalance = account.getBalance();
        if (currentBalance < amount) {
            throw new InsufficientBalanceException("Insufficient balance");
        }

        double newBalance = currentBalance - amount;
        account.setBalance(newBalance);
        accountRep.save(account);

        TransactionEntity transaction = new TransactionEntity();
        transaction.setAmount(amount);
        transaction.setTransactionType(TransactionTypeEntity.CASH_WITHDRAWAL);
        transaction.setTransaction_date(new Date());
        transaction.setSourceAccount(account);
        transactionRep.save(transaction);
    }
    
    @Override
    public void fundTransfer(String sourceAccountNumber, String targetAccountNumber, String pin, double amount) {
        AccountEntity sourceAccount = accountRep.findAccountByNumber(sourceAccountNumber);
        if (sourceAccount == null) {
            throw new NotFoundException("Source account not found");
        }

        AccountEntity targetAccount = accountRep.findAccountByNumber(targetAccountNumber);
        if (targetAccount == null) {
            throw new NotFoundException("Target account not found");
        }

        if (!passwordEncoder.matches(pin, sourceAccount.getPin())) {
            throw new UnauthorizedException("Invalid PIN");
        }

        double sourceBalance = sourceAccount.getBalance();
        if (sourceBalance < amount) {
            throw new InsufficientBalanceException("Insufficient balance");
        }

        double newSourceBalance = sourceBalance - amount;
        sourceAccount.setBalance(newSourceBalance);
        accountRep.save(sourceAccount);

        double targetBalance = targetAccount.getBalance();
        double newTargetBalance = targetBalance + amount;
        targetAccount.setBalance(newTargetBalance);
        accountRep.save(targetAccount);

        TransactionEntity transaction = new TransactionEntity();
        transaction.setAmount(amount);
        transaction.setTransactionType(TransactionTypeEntity.CASH_TRANSFER);
        transaction.setTransaction_date(new Date());
        transaction.setSourceAccount(sourceAccount);
        transaction.setTargetAccount(targetAccount);
        transactionRep.save(transaction);
    }
    
}
