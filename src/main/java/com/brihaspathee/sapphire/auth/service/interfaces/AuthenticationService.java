package com.brihaspathee.sapphire.auth.service.interfaces;

import com.brihaspathee.sapphire.dto.auth.UserDto;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 21, February 2025
 * Time: 3:15 PM
 * Project: sapphire
 * Package Name: com.brihaspathee.sapphire.auth.service
 * To change this template use File | Settings | File and Code Template
 */
public interface AuthenticationService {

    /**
     * Validates the provided token and returns the corresponding user details.
     *
     * @param token the token to be validated
     * @return the details of the user associated with the token
     */
    UserDto validateToken(String token);
}
