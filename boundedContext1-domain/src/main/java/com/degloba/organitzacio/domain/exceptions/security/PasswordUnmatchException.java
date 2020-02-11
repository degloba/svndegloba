package com.degloba.organitzacio.domain.exceptions.security;

/**
 */
public class PasswordUnmatchException extends SecurityException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PasswordUnmatchException() {
    }

    public PasswordUnmatchException(String message) {
        super(message);
    }

    public PasswordUnmatchException(String message, Throwable cause) {
        super(message, cause);
    }

    public PasswordUnmatchException(Throwable cause) {
        super(cause);
    }

    public PasswordUnmatchException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
