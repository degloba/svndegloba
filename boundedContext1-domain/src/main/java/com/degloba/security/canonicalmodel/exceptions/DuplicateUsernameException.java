package com.degloba.security.canonicalmodel.exceptions;

/**
 * Username repeated anomalies
 */
public class DuplicateUsernameException extends SecurityException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DuplicateUsernameException() {
        super();
    }

    public DuplicateUsernameException(String message) {
        super(message);
    }

    public DuplicateUsernameException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateUsernameException(Throwable cause) {
        super(cause);
    }

    protected DuplicateUsernameException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
