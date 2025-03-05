package com.brihaspathee.sapphire.controller.impl;

import com.brihaspathee.sapphire.auth.SapphireUserDetailsService;
import com.brihaspathee.sapphire.auth.service.JwtService;
import com.brihaspathee.sapphire.auth.service.interfaces.AuthenticationService;
import com.brihaspathee.sapphire.controller.interfaces.AuthenticationAPI;
import com.brihaspathee.sapphire.domain.entity.User;
import com.brihaspathee.sapphire.dto.auth.AuthorizationRequest;
import com.brihaspathee.sapphire.dto.auth.UserDto;
import com.brihaspathee.sapphire.model.AuthenticationRequest;
import com.brihaspathee.sapphire.model.AuthenticationResponse;
import com.brihaspathee.sapphire.web.response.SapphireAPIResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 21, February 2025
 * Time: 8:58 AM
 * Project: sapphire
 * Package Name: com.brihaspathee.sapphire.controller.impl
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthenticationAPIImpl implements AuthenticationAPI {

    /**
     * Manages the authentication process by validating user credentials.
     * Used to authenticate the user's username and password combination.
     */
    private final AuthenticationManager authenticationManager;

    /**
     * Handles user-specific authentication logic by integrating with the
     * underlying database to fetch and validate user details. This service
     * is responsible for loading and managing user credentials during the
     * authentication process.
     */
    private final SapphireUserDetailsService sapphireUserDetailsService;

    /**
     * Provides functionality for generating and validating JWT tokens.
     * This service is used to create access tokens for authenticated users and
     * to validate token authenticity during secure operations.
     */
    private final JwtService jwtService;

    /**
     * Authenticates a user using the provided authentication request, which contains
     * the username and password. The method validates the credentials, generates a JWT
     * access token upon successful authentication, and returns the response with status
     * and details of the authentication process.
     *
     * @param authenticationRequest the authentication request containing the username and password
     * @return a ResponseEntity containing a SapphireAPIResponse object with an AuthenticationResponse,
     *         which includes the generated access token, authentication status, and other metadata
     */
    @Override
    public ResponseEntity<SapphireAPIResponse<AuthenticationResponse>> authenticate(AuthenticationRequest authenticationRequest) {
        UserDetails userDetails = null;
        try{
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(),
                    authenticationRequest.getPassword()));
            /*
                Once the user is authenticated, get the user details from the authentication object
             */
            userDetails = (UserDetails) authentication.getPrincipal();
        }catch (BadCredentialsException e){
            throw new BadCredentialsException("Invalid credentials");
        }
        if(userDetails instanceof User){
            /*
              once the user is retrieved, generate the access token (jwt) using the details of the user
             */
            User user = (User) userDetails;
            final String accessToken = jwtService.generateToken(user);
            /*
                Create the authentication response with the token and send it back to the user.
             */
            AuthenticationResponse authenticationResponse = AuthenticationResponse.builder()
                    .accessToken(accessToken)
                    .build();
            SapphireAPIResponse<AuthenticationResponse> response = SapphireAPIResponse.<AuthenticationResponse>builder()
                    .response(authenticationResponse)
                    .status(HttpStatus.OK)
                    .reason("Authentication Success")
                    .message("User Successfully authenticated")
                    .developerMessage("User Successfully authenticated")
                    .timestamp(LocalDateTime.now())
                    .build();
            return ResponseEntity.ok(response);
        }else{
            throw new RuntimeException("User not found");
        }
    }

}
