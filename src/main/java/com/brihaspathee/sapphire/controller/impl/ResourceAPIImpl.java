package com.brihaspathee.sapphire.controller.impl;

import com.brihaspathee.sapphire.auth.service.interfaces.AuthenticationService;
import com.brihaspathee.sapphire.controller.interfaces.ResourceAPI;
import com.brihaspathee.sapphire.dto.auth.AuthorizationRequest;
import com.brihaspathee.sapphire.dto.auth.UserDto;
import com.brihaspathee.sapphire.dto.resource.ResourceRegistrationRequest;
import com.brihaspathee.sapphire.dto.resource.ResourceRegistrationResponse;
import com.brihaspathee.sapphire.model.ResourceDto;
import com.brihaspathee.sapphire.services.interfaces.ResourceManagementService;
import com.brihaspathee.sapphire.web.response.SapphireAPIResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 24, February 2025
 * Time: 4:00 PM
 * Project: sapphire
 * Package Name: com.brihaspathee.sapphire.controller.impl
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class ResourceAPIImpl implements ResourceAPI {

    /**
     * Service responsible for managing resource-related operations.
     * This includes registering new resources and retrieving details
     * of existing resources.
     */
    private final ResourceManagementService resourceManagementService;

    /**
     * Provides authentication-related services such as handling user authentication
     * and validation. Responsible for coordinating the authentication flow and ensuring
     * proper access control by managing user credentials and authentication tokens.
     */
    private final AuthenticationService authenticationService;

    /**
     * Registers a new resource in the system based on the provided registration request.
     *
     * @param registrationRequest the request object containing details necessary to register the resource
     * @return a ResponseEntity containing a SapphireAPIResponse object wrapping a ResourceRegistrationResponse,
     *         which includes the details of the registered resource
     */
    @Override
    public ResponseEntity<SapphireAPIResponse<ResourceRegistrationResponse>> registerResource(ResourceRegistrationRequest registrationRequest) {
        return null;
    }

    /**
     * Retrieves the permissions associated with the specified resource URI.
     *
     * @param resourceURI the unique URI of the resource whose permissions are to be retrieved
     * @return a ResponseEntity containing a SapphireAPIResponse object wrapping a ResourceDto
     */
    @Override
    public ResponseEntity<SapphireAPIResponse<ResourceDto>> getPermission(String resourceURI) {
        ResourceDto resourceDto = resourceManagementService.getResource(resourceURI);
        if(resourceDto != null) {
            SapphireAPIResponse<ResourceDto> response = SapphireAPIResponse.<ResourceDto>builder()
                    .response(resourceDto)
                    .developerMessage("Successfully fetched the resource permissions")
                    .statusCode(200)
                    .message("Success")
                    .reason("Success")
                    .status(org.springframework.http.HttpStatus.OK)
                    .build();
            return ResponseEntity.ok(response);
        }
        return null;
    }

    /**
     * Validates whether a given user has access to the requested resource based on their authorities.
     * This method processes the authentication details of the user and checks
     * against the required permissions for the resource.
     *
     * @param userDetails the details of the authenticated user making the request
     * @param authorizationRequest the request object containing the resource details and required authorization data
     * @return a ResponseEntity containing a SapphireAPIResponse object that includes user details if the access is valid
     */
    @Override
    public ResponseEntity<SapphireAPIResponse<UserDto>> validateResourceAccess(@AuthenticationPrincipal UserDetails userDetails,
                                                                               @Valid @RequestBody AuthorizationRequest authorizationRequest){
        /*
            This is a secured endpoint, so if the control reaches to the controller, the user is already authenticated
            and the security context holder will have the authenticated user details
            This user details is being fetched from the database in the SapphireUserDetails service
            At that point all the authorities associated with the user is also fetched.
            So, with the user details and their authorities already available at the time
            the control reaches here, the only thing needed to be done is to get the
            authorities needed for the resource and compare it with the authorities of the user
            and if the user has the authority then return the username. This logic of comparing the
            user's authorities with the authority need to access the resource is done in the authentication service
         */
        log.info("Validating the token");
        UserDto userDto = authenticationService.validateResourceAccess(userDetails, authorizationRequest);
        SapphireAPIResponse<UserDto> response = SapphireAPIResponse.<UserDto>builder()
                .response(userDto)
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .reason("Token Validation Success")
                .message("Token Successfully validated")
                .developerMessage("Token Successfully validated")
                .statusCode(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(response);
    }
}
