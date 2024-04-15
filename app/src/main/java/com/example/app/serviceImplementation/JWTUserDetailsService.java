package com.example.app.serviceImplementation;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.app.entity.UserEntity;
import com.example.app.repository.UserRepo;

@Service
public class JWTUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepository;

    @Override
    public UserDetails loadUserByUsername(String accountNumber) throws UsernameNotFoundException {

        UserEntity user = userRepository.getUserByAccountNumber(accountNumber);
        
        if (user == null) {
            throw new UsernameNotFoundException("Invalid account number");
        }

        // Return a UserDetails object that wraps the User entity
        return new org.springframework.security.core.userdetails.User(
                user.getAccount().getAccountNumber(),  // Use account number as the username
                user.getPassword(),
                Collections.emptyList()
        );
    }	
}
