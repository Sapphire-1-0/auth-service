package com.brihaspathee.sapphire.auth.service.interfaces;

import com.brihaspathee.sapphire.dto.auth.AuthorizationRequest;
import com.brihaspathee.sapphire.dto.auth.UserDto;
import org.springframework.security.core.userdetails.UserDetails;

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
     * Validates whether a user has the necessary access to a specified resource.
     *
     * @param userDetails the details of the user requesting access, containing user-specific information
     * @param authorizationRequest the information about the resource being accessed, including its URI
     * @return a UserDto object containing user details, including roles and access rights, if the user has valid access
     */
    UserDto validateResourceAccess(UserDetails userDetails,
                          AuthorizationRequest authorizationRequest);
}
