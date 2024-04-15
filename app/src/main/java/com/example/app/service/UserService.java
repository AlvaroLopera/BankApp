package com.example.app.service;

import org.springframework.stereotype.Service;

import com.example.app.entity.UserEntity;

@Service
public interface UserService {
    
    UserEntity registerUser(UserEntity user);

	UserEntity getUserByAccountNumber(String account_no);

	void saveUser(UserEntity user);

	UserEntity updateUser(UserEntity user);

}
