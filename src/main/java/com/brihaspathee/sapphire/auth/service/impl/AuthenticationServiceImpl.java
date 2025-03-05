package com.brihaspathee.sapphire.auth.service.impl;

import com.brihaspathee.sapphire.auth.service.JwtService;
import com.brihaspathee.sapphire.auth.service.interfaces.AuthenticationService;
import com.brihaspathee.sapphire.domain.entity.Authority;
import com.brihaspathee.sapphire.domain.entity.User;
import com.brihaspathee.sapphire.domain.repository.UserRepository;
import com.brihaspathee.sapphire.dto.auth.AuthorityDto;
import com.brihaspathee.sapphire.dto.auth.AuthorizationRequest;
import com.brihaspathee.sapphire.dto.auth.UserDto;
import com.brihaspathee.sapphire.exception.AccessDeniedException;
import com.brihaspathee.sapphire.mapper.interfaces.UserMapper;
import com.brihaspathee.sapphire.model.ResourceDto;
import com.brihaspathee.sapphire.services.interfaces.ResourceManagementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

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
     * Represents a service instance used for managing resources within the application.
     * Provides functionality to handle operations related to resource allocation,
     * permissions, and accessibility based on user roles and configurations.
     * This service acts as a part of the application's core to ensure proper
     * resource management and security.
     */
    private final ResourceManagementService resourceManagementService;

    /**
     * Validates if a user has the necessary permissions to access a specified resource.
     *
     * @param userDetails the details of the user attempting to access the resource, including their authorities
     * @param authorizationRequest the request detailing the resource URI the user wants to access
     * @return a UserDto object containing the username of the authorized user
     * @throws AccessDeniedException if the user does not have permission to access the resource
     */
    @Override
    public UserDto validateResourceAccess(UserDetails userDetails, AuthorizationRequest authorizationRequest) {
        List<String> userAuthorities = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
        log.info("User authorities: {}", userAuthorities);
        ResourceDto resourceDto = resourceManagementService.getResource(authorizationRequest.getResourceUri());
        List<String> resourceAuthorities = resourceDto.getAuthorities().stream().map(AuthorityDto::getPermission).toList();
        log.info("Resource authorities: {}", resourceAuthorities);
        if(!isUserAuthorized(resourceAuthorities, userAuthorities)) {
            throw new AccessDeniedException("User is not authorized to access the resource");
        }
        if(userDetails instanceof User user) {
            log.info("User: {}", user);
            log.info("User's Authorities: {}", user.getAuthorities());
            log.info("User's username: {}", user.getUsername());
            log.info("User's serviceId: {}", user.getServiceId());
            log.info("User's account type: {}", user.getAccountType());
            return userMapper.toUserDto(user);
        }
        return UserDto.builder().username(userDetails.getUsername()).build();
    }

    /**
     * Checks if a user is authorized to access a resource based on the authorities assigned
     * to the resource and the user.
     *
     * - `Collections.disjoint` is a utility method from Java's `Collections` framework that checks whether two
     *    collections have no elements in common.
     *     - If the collections are **disjoint** (i.e., have no common elements), it returns `true`.
     *     - If they **do** share common elements, it returns `false`.
     *
     * - This method uses the negation operator (`!`) with `Collections.disjoint`. So:
     *     - If the collections are **NOT disjoint** (i.e., they have common elements), the method returns `true`, meaning the user is authorized.
     *     - If the collections are disjoint (i.e., **no common elements**), the method returns `false`, meaning the user is not authorized.
     *
     * @param resourceAuthorities the list of authorities required to access the resource.
     * @param userAuthorities the list of authorities assigned to the user.
     * @return true if the user is authorized to access the resource, false otherwise.
     */
    private boolean isUserAuthorized(List<String> resourceAuthorities, List<String> userAuthorities) {
        return !Collections.disjoint(resourceAuthorities, userAuthorities);
    }
}
