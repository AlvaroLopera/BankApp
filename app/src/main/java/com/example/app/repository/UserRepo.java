package com.example.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.app.entity.UserEntity;

@Repository
public interface UserRepo extends JpaRepository <UserEntity, Long> {
    
    UserEntity getUserById ( String id );

    UserEntity getUserByEmail ( String email );

    UserEntity getUserByAccountNumber ( String accountNumber );

}
