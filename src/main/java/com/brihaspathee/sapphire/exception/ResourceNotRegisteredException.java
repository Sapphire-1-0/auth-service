package com.brihaspathee.sapphire.exception;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 24, February 2025
 * Time: 5:02 PM
 * Project: sapphire
 * Package Name: com.brihaspathee.sapphire
 * To change this template use File | Settings | File and Code Template
 */
public class ResourceNotRegisteredException extends RuntimeException {
    /**
     * Constructs a new ResourceNotRegisteredException with the specified detail message.
     *
     * @param message the detail message that describes the exception.
     */
    public ResourceNotRegisteredException(String message) {
        super(message);
    }

    /**
     * Constructs a new ResourceNotRegisteredException with the specified
     * detail message and cause.
     *
     * @param message the detail message that describes the exception.
     * @param cause the cause of the exception (can be null).
     */
    public ResourceNotRegisteredException(String message, Throwable cause) {
        super(message, cause);
    }
}
