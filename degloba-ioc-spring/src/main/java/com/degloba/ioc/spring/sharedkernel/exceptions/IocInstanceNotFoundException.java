package com.degloba.ioc.spring.sharedkernel.exceptions;

/**
 * @author degloba
 * 
 * @category Excepció llançada quan s'intenta recuperar una instancia bean d'un contenidor IOC que no existeix.
 */
public class IocInstanceNotFoundException extends IocException {

	private static final long serialVersionUID = -742775077430352894L;

	public IocInstanceNotFoundException() {
	}

	public IocInstanceNotFoundException(String message) {
		super(message);
	}

	public IocInstanceNotFoundException(Throwable cause) {
		super(cause);
	}

	public IocInstanceNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

}
