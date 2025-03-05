package com.brihaspathee.sapphire.exception;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 25, February 2025
 * Time: 9:23 PM
 * Project: sapphire
 * Package Name: com.brihaspathee.sapphire.exception
 * To change this template use File | Settings | File and Code Template
 */
public class AccessDeniedException extends RuntimeException {
    public AccessDeniedException(String message) {
        super(message);
    }
    public AccessDeniedException(String message, Throwable cause) {
    }
}
