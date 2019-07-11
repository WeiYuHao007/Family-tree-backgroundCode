package com.orange.familyTree.exceptions;

public class AccountException extends RuntimeException{
	/**
	 * 
	 */
	private String message;
	
	public AccountException(String message) {
		super(message);
		this.message = message;
	}
}
