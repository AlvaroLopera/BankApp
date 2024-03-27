package com.example.app.service;

import com.example.app.entity.UserEntity;

public interface UserService {
    
    UserEntity registerUser(UserEntity user);

	UserEntity getUserByAccountNumber(String account_no);

	void saveUser(UserEntity user);

	UserEntity updateUser(UserEntity user);

}
