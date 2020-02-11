package com.degloba.security.domain.exceptions;

/**
 */
public class DuplicateAuthorizationException extends SecurityException {

	private static final long serialVersionUID = 1L;

	public DuplicateAuthorizationException() {
    }

    public DuplicateAuthorizationException(String message) {
        super(message);
    }

    public DuplicateAuthorizationException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateAuthorizationException(Throwable cause) {
        super(cause);
    }

    public DuplicateAuthorizationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
