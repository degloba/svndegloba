package com.degloba.organitzacio.domain.exceptions.security;

/**
 */
public class UserDisabledException extends SecurityException {

	private static final long serialVersionUID = 1L;

	public UserDisabledException() {
    }

    public UserDisabledException(String message) {
        super(message);
    }

    public UserDisabledException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserDisabledException(Throwable cause) {
        super(cause);
    }

    public UserDisabledException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
