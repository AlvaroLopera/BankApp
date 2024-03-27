package com.example.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.example.app.entity.OtpInfoEntity;

@Service
public interface OtpRepo extends JpaRepository<OtpInfoEntity, Long> {
    
    OtpInfoEntity findByAccountNumberAndOtp(String accountNumber, String otp);
	
	OtpInfoEntity findByAccountNumber(String accountNumber);

}
