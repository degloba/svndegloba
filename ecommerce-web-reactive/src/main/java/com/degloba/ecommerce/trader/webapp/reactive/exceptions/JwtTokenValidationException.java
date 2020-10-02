package com.degloba.ecommerce.trader.webapp.reactive.exceptions;

public class JwtTokenValidationException extends RuntimeException {

	private static final long serialVersionUID = -1119114699758662609L;

	public JwtTokenValidationException(String message) {
		super(message);
	}
	
	public JwtTokenValidationException(String message, Throwable th) {
		super(message, th);
	}
}