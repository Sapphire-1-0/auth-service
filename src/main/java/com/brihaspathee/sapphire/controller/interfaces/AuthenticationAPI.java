package com.brihaspathee.sapphire.controller.interfaces;

import com.brihaspathee.sapphire.dto.auth.AuthorizationRequest;
import com.brihaspathee.sapphire.dto.auth.UserDto;
import com.brihaspathee.sapphire.model.AuthenticationRequest;
import com.brihaspathee.sapphire.model.AuthenticationResponse;
import com.brihaspathee.sapphire.web.response.SapphireAPIResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 20, February 2025
 * Time: 3:46 PM
 * Project: sapphire
 * Package Name: com.brihaspathee.sapphire.controller.interfaces
 * To change this template use File | Settings | File and Code Template
 */
@RequestMapping("/api/v1/sapphire/auth/public")
public interface AuthenticationAPI {


    /**
     * Authenticates a user based on the provided credentials in the authentication request.
     * If the authentication is successful, an access token is issued and returned in the response.
     *
     * @param authenticationRequest the authentication request containing the username and password
     * @return a ResponseEntity containing a SapphireAPIResponse object with the authentication response,
     *         including the access token, or an appropriate error message in case of failure
     */
    @PostMapping("/authenticate")
    ResponseEntity<SapphireAPIResponse<AuthenticationResponse>> authenticate(@Valid
                                                                             @RequestBody
                                                                             AuthenticationRequest
                                                                                     authenticationRequest);
}
