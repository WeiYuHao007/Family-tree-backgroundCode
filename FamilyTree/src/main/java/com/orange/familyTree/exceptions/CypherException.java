package com.orange.familyTree.exceptions;

public class CypherException extends RuntimeException{
	/**
	 * 
	 */
	private String message;
	
	public CypherException(String message) {
		super(message);
		this.message = message;
	}
}
