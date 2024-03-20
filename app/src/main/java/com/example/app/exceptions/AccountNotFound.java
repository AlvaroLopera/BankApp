package com.example.app.exceptions;

public class AccountNotFound extends RuntimeException {
    
	private static final long serialVersionUID = 1180822918717228267L;

    private AccountNotFound ( String msg ) {
        super(msg);
    }

}
