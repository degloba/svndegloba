package com.degloba.ioc.spring.sharedkernel.exceptions;

/**
 * @author degloba
 * 
 * @category Excepció llançada quan hi ha més d'una instància de bean de un tipus.Aquesta classe SI està vinculada a IOC (no necesariament Spring)
 * 
 */
public class IocInstanceNotUniqueException extends IocException {

	private static final long serialVersionUID = -742775077430352894L;

	public IocInstanceNotUniqueException() {
	}

	public IocInstanceNotUniqueException(String message) {
		super(message);
	}

	/**
	 * 
	 * @param cause error o excepció Java
	 */
	public IocInstanceNotUniqueException(Throwable cause) {
		super(cause);
	}

	public IocInstanceNotUniqueException(String message, Throwable cause) {
		super(message, cause);
	}

}
