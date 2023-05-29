package com.intellewings.exception;

public class BookNotFoundException extends RuntimeException {

	public BookNotFoundException() {
		
	}
	
	public BookNotFoundException(String message) {
		super(message);
	}
}
