package com.degloba.security.canonicalmodel.exceptions;

/**
 */
public class UserNotFoundException extends SecurityException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserNotFoundException() {
    }

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotFoundException(Throwable cause) {
        super(cause);
    }

    public UserNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
