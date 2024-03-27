package com.example.app.service;

import org.springframework.stereotype.Service;

import com.example.app.dto.AccountResponse;
import com.example.app.dto.UserResponse;

@Service
public interface DashboardService {

    UserResponse getUserDetails(String accountNumber);
    
    AccountResponse getAccountDetails(String accountNumber);
    
} 