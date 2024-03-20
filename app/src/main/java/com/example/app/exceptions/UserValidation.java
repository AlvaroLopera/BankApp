package com.example.app.exceptions;

public class UserValidation extends RuntimeException {
    
    public UserValidation ( String msg ) {
        super(msg);
    }

}
