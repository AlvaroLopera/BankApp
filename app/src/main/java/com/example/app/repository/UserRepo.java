package com.example.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.example.app.entity.UserEntity;

@Service
public interface UserRepo extends JpaRepository <UserEntity, Long> {
    
    UserEntity getUserById ( String id );

    UserEntity getUserByEmail ( String email );

    UserEntity getUserByAccountNumber ( String number );

}
