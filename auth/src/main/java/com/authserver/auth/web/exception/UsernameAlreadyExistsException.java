package com.authserver.auth.web.exception;

public class UsernameAlreadyExistsException extends Exception{

	private static final long serialVersionUID = 1423565713220199588L;
	
	  /**
     * Base exception constructor
     */
    public UsernameAlreadyExistsException() {
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
    public UsernameAlreadyExistsException(String message, Throwable cause, boolean enableSuppression,
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
    public UsernameAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor with message
     *
     * @param message
     *            the message
     */
    public UsernameAlreadyExistsException(String message) {
        super(message);
    }

    /**
     * Constructor with a cause
     *
     * @param message
     *            the cause
     */
    public UsernameAlreadyExistsException(Throwable cause) {
        super(cause);
    }

}
