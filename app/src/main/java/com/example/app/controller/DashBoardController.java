package com.example.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.dto.AccountResponse;
import com.example.app.dto.UserResponse;
import com.example.app.service.DashboardService;
import com.example.app.util.LoggedinUser;

@RestController
@RequestMapping("/api/dashboard")
public class DashBoardController {
    
    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/user")
    public ResponseEntity<UserResponse> getUserDetails() {
        String accountNumber = LoggedinUser.getAccountNumber();
        UserResponse userResponse = dashboardService.getUserDetails(accountNumber);
        return ResponseEntity.ok(userResponse);
    }

    @GetMapping("/account")
    public ResponseEntity<AccountResponse> getAccountDetails() {
        String accountNumber = LoggedinUser.getAccountNumber();
        AccountResponse accountResponse = dashboardService.getAccountDetails(accountNumber);
        return ResponseEntity.ok(accountResponse);
    }

}
