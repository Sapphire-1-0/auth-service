package com.brihaspathee.sapphire.exception;

import javax.naming.AuthenticationException;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 05, January 2025
 * Time: 6:45 AM
 * Project: sapphire
 * Package Name: com.brihaspathee.sapphire.exception
 * To change this template use File | Settings | File and Code Template
 */
public class UserNotFoundException extends RuntimeException {

    /**
     * Constructor for the exception
     * @param msg - the exception message
     */
    public UserNotFoundException(String msg) {
        super(msg);
    }

}
