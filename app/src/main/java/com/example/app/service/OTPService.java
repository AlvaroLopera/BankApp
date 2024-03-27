package com.example.app.service;

import java.util.concurrent.CompletableFuture;

public interface OTPService {
    
    String generateOTP(String accountNumber);

	CompletableFuture<Boolean> sendOTPByEmail(String email,String name,String accountNumber, String otp);

	boolean validateOTP(String accountNumber, String otp);
}
