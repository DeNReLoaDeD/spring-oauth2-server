package com.authserver.auth.web.exception;

public class UserNotFoundException extends Exception{

	private static final long serialVersionUID = 1423565713220199588L;
	
	  /**
     * Base exception constructor
     */
    public UserNotFoundException() {
        super();
    }

    /**
     * Constructor
     *
     * @param message
     *            of the exeception
     * @param cause
     *            provoque
     * @param enableSuppression
     *            for enabling supression
     * @param writableStackTrace
     *            if write it
     */
    public UserNotFoundException(String message, Throwable cause, boolean enableSuppression,
                                       boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    /**
     * Constructor with message and a cause
     *
     * @param message
     *            the message
     * @param cause
     *            the cause
     */
    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor with message
     *
     * @param message
     *            the message
     */
    public UserNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructor with a cause
     *
     * @param message
     *            the cause
     */
    public UserNotFoundException(Throwable cause) {
        super(cause);
    }

}
