package com.degloba.domain;

/**
 * IoC Vessel abnormalities. 
 * This exception is thrown or subclass instance when accessing IoC container (Spring, Guice, etc.) exception occurs.
 */
public class IocException extends RuntimeException {

	private static final long serialVersionUID = -2359843215972162510L;

	public IocException() {
	}

	public IocException(String message) {
		super(message);
	}

	public IocException(Throwable cause) {
		super(cause);
	}

	public IocException(String message, Throwable cause) {
		super(message, cause);
	}

}
