package com.example.app.serviceImplementation;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.app.entity.AccountEntity;
import com.example.app.entity.UserEntity;
import com.example.app.exceptions.UserValidation;
import com.example.app.repository.UserRepo;
import com.example.app.service.AccountService;
import com.example.app.service.UserService;

@Service
public class UserServiceImp implements UserService {

	private final UserRepo userRepository;
    private final AccountService accountService;
    private final PasswordEncoder passwordEncoder;


    public UserServiceImp(UserRepo userRepository, AccountService accountService,PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.accountService = accountService;
        this.passwordEncoder =  passwordEncoder;
    }
    
    @Override
    public UserEntity getUserByAccountNumber(String account_no) {
    	return userRepository.getUserByAccountNumber(account_no);
    }
    
    @Override
    public UserEntity registerUser(UserEntity user) {
        
    	 String encodedPassword = passwordEncoder.encode(user.getPassword());
         user.setPassword(encodedPassword);

        // Save the user details
        UserEntity savedUser = userRepository.save(user);

        // Create an account for the user
        AccountEntity account = accountService.createAccount(savedUser);

        savedUser.setAccount(account);
        userRepository.save(savedUser);
        
        System.out.println(savedUser.getAccount().getAccountNumber());
        System.out.println(account.getUser().getName());

        
        return savedUser;
    }

	@Override
	public void saveUser(UserEntity user) {
		userRepository.save(user);
		
	}

    @Override
    public UserEntity updateUser(UserEntity user) {
        /*
        UserEntity existingUser = userRepository.findByAccountAccountNumber(LoggedinUser.getAccountNumber());
        if(user.getEmail() != null){
            if(user.getEmail().isEmpty())
                throw new UserValidation("Email can't be empty");
            else
                existingUser.setEmail(user.getEmail());
        }
        if(user.getName() != null){
            if(user.getName().isEmpty())
                throw new UserValidation("Name can't be empty");
            else
                existingUser.setName(user.getName());
        }
        if(user.getPhone_number() != null){
            if(user.getPhone_number().isEmpty())
                throw new UserValidation("Phone number can't be empty");
            else
                existingUser.setPhone_number(user.getPhone_number());
        }
        if(user.getAddress() != null){
            existingUser.setAddress(user.getAddress());
        }
        return userRepository.save(existingUser);
    */
    }
}