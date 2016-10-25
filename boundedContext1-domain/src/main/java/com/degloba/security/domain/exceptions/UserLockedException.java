package com.degloba.security.domain.exceptions;

/**
 */
public class UserLockedException extends SecurityException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserLockedException() {
    }

    public UserLockedException(String message) {
        super(message);
    }

    public UserLockedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserLockedException(Throwable cause) {
        super(cause);
    }

    public UserLockedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
