package com.brihaspathee.sapphire.auth.service.impl;

import com.brihaspathee.sapphire.auth.service.JwtService;
import com.brihaspathee.sapphire.auth.service.interfaces.AuthenticationService;
import com.brihaspathee.sapphire.domain.entity.User;
import com.brihaspathee.sapphire.domain.repository.UserRepository;
import com.brihaspathee.sapphire.dto.auth.UserDto;
import com.brihaspathee.sapphire.mapper.interfaces.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 21, February 2025
 * Time: 3:17 PM
 * Project: sapphire
 * Package Name: com.brihaspathee.sapphire.auth.service.impl
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    /**
     * The JwtService instance is responsible for handling operations related to JSON Web Tokens (JWT).
     * This service provides functionality such as generating, validating, and extracting details
     * from JWT tokens to facilitate secure authentication and authorization processes.
     * It is injected as a dependency to enable token management within the application.
     */
    private final JwtService jwtService;

    /**
     * This variable holds a reference to the UserRepository, which provides methods
     * for database operations related to the User entity. It acts as the data access
     * layer for retrieving and managing User information, such as finding users
     * by their username.
     */
    private final UserRepository userRepository;

    /**
     * Represents an instance of the UserMapper interface, which is responsible for
     * transforming User entity objects to UserDto objects and vice versa.
     * This enables a seamless and modular approach to data mapping between
     * the application's domain layer and its data transfer layer.
     */
    private final UserMapper userMapper;

    /**
     * Validates the provided JWT token, extracts the username, retrieves user details,
     * and maps them to a UserDto object if valid.
     *
     * @param token The JWT token to be validated.
     * @return A UserDto object containing the details of the authenticated user.
     * @throws BadCredentialsException if the username cannot be extracted, the user is not found,
     * or the token is invalid.
     */
    @Override
    public UserDto validateToken(String token) {
        log.info("Validating the token");
        Long userId = jwtService.extractUserId(token);
        log.info("Extracted userId: {}", userId);
        if(userId == null) {
            throw new BadCredentialsException("Unable to extract username from the token");
        }
        User user = userRepository.findById(userId).orElse(null);
        if(user == null) {
            throw new BadCredentialsException("User not found");
        }
        if(jwtService.validateToken(token, user)) {
           return userMapper.toUserDto(user);
        }else{
            throw new BadCredentialsException("Invalid token");
        }
    }
}
