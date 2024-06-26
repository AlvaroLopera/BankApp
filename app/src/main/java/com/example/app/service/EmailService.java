package com.example.app.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.stereotype.Service;

@Service
public interface EmailService {

    public CompletableFuture<Void> sendEmail(String to, String subject, String text);

    public String getOtpLoginEmailTemplate(String name,String accountNumber, String otp);

}   