package com.example.app.util;

import org.springframework.security.core.context.SecurityContextHolder;

import com.example.app.exceptions.NotFoundException;

public class LoggedinUser {
    
    public static String getAccountNumber() {
	    /*    
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	        
        if (authentication != null) {
	            
            Object principal = authentication.getPrincipal();
	            
	            if (principal instanceof User) {
	            	 
                    User user = (User) principal;
	                 
                    
                    return user.getUsername();
	            }
	        }
	        throw new NotFoundException("Account number not found in Security Context.");
	}
*/
    return null;
}
}