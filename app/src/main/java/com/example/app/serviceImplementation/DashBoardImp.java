package com.example.app.serviceImplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.dto.AccountResponse;
import com.example.app.dto.UserResponse;
import com.example.app.entity.AccountEntity;
import com.example.app.entity.UserEntity;
import com.example.app.exceptions.NotFoundException;
import com.example.app.repository.AccountRepo;
import com.example.app.repository.UserRepo;
import com.example.app.service.DashboardService;


@Service
public class DashBoardImp implements DashboardService {

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private AccountRepo accountRepository;


    @Override
    public UserResponse getUserDetails(String accountNumber) {
       
        UserEntity user = userRepository.getUserByAccountNumber(accountNumber);
        
        // Check if the user exists and is associated with the given account number
        if (user == null) {
            throw new NotFoundException("User not found for the provided account number.");
        }

        // Map the user entity to UserResponse DTO
        UserResponse userResponse = new UserResponse();
        userResponse.setName(user.getName());
        userResponse.setEmail(user.getEmail());
        userResponse.setAddress(user.getAddress());
        userResponse.setPhone_number(user.getPhone_number());
        userResponse.setAccountNumber(user.getAccount().getAccountNumber());

        return userResponse;

    }

    @Override
    public AccountResponse getAccountDetails(String accountNumber) {

        AccountEntity account = accountRepository.findAccountByNumber(accountNumber);
        // Check if the account exists with the provided account number
        if (account == null) {
            throw new NotFoundException("Account not found for the provided account number.");
        }

        // Map the account entity to AccountResponse DTO
        AccountResponse accountResponse = new AccountResponse();
        accountResponse.setAccountNumber(account.getAccountNumber());
        accountResponse.setAccountType(account.getAccount_type());
        accountResponse.setBalance(account.getBalance());
        accountResponse.setBranch(account.getBranch());
        accountResponse.setIFSCCode(account.getIFSC_code());

        return accountResponse;
    }
    
}
