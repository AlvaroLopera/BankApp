package com.example.app.exceptions;

public class UnauthorizedException extends RuntimeException {
    private static final long serialVersionUID = -4893320765855582206L;

	public UnauthorizedException(String msg) {
		super(msg);
	}
}
