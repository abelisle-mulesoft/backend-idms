package com.umbrella.idms.exception;

/**
 * @author Alain Belisle
 */
public class IdentityAlreadyExistsException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructs an exception with no detail message.
     */
    public IdentityAlreadyExistsException() {

        super();
    }

    /**
     * Constructs an exception with the given detail message.
     *
     * @param message The detail message.
     */
    public IdentityAlreadyExistsException(String message) {

        super(message);
    }

    /**
     * Constructs an exception with the given detail message and cause.
     *
     * @param cause The cause.
     */
    public IdentityAlreadyExistsException(Throwable cause) {

        super(cause);
    }

    /**
     * Constructs an exception with the given detail message and cause.
     *
     * @param message The detail message.
     * @param cause   The cause.
     */
    public IdentityAlreadyExistsException(String message, Throwable cause) {

        super(message, cause);
    }

}
