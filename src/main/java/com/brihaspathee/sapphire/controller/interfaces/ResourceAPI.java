package com.brihaspathee.sapphire.controller.interfaces;

import com.brihaspathee.sapphire.dto.auth.AuthorizationRequest;
import com.brihaspathee.sapphire.dto.auth.UserDto;
import com.brihaspathee.sapphire.dto.resource.ResourceRegistrationRequest;
import com.brihaspathee.sapphire.dto.resource.ResourceRegistrationResponse;
import com.brihaspathee.sapphire.model.ResourceDto;
import com.brihaspathee.sapphire.model.UserList;
import com.brihaspathee.sapphire.web.response.SapphireAPIResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 23, February 2025
 * Time: 8:58 AM
 * Project: sapphire
 * Package Name: com.brihaspathee.sapphire.controller.interfaces
 * To change this template use File | Settings | File and Code Template
 */
@RequestMapping("/api/v1/sapphire/auth/resource")
@Validated
public interface ResourceAPI {

    /**
     * Registers a resource with appropriate permissions.
     *
     * @param registrationRequest the request object containing the details needed
     *                            to register the resource, such as resource identity
     *                            and attributes.
     * @return a ResponseEntity containing a SapphireAPIResponse that encapsulates a
     *         ResourceRegistrationResponse object, which provides detailed information
     *         about the registered resource, including an identifier and status message.
     */
    @Operation(
            method = "POST",
            description = "Register the resource with appropriate permissions",
            tags = {"resource"}
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Successfully registered the resource",
                            content = {
                                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ResourceRegistrationResponse.class))
                            }
                    )
            }
    )
    @PostMapping
    ResponseEntity<SapphireAPIResponse<ResourceRegistrationResponse>> registerResource(@Valid @RequestBody
                                                                                       ResourceRegistrationRequest registrationRequest);

    /**
     * Retrieves the permission details associated with the specified resource.
     *
     * @param resourceURI the URI of the resource whose permission details are being requested
     * @return a ResponseEntity containing a SapphireAPIResponse object with the
     *         retrieved permission details as a String
     */
    @Operation(
            method = "POST",
            description = "Get the permission associated with the resource",
            tags = {"resource"}
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved the permission",
                            content = {
                                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = String.class))
                            }
                    )
            }
    )
    @PostMapping("/permission")
    ResponseEntity<SapphireAPIResponse<ResourceDto>> getPermission(@Valid @RequestBody String resourceURI);

    /**
     * Validates if the user has access permissions to a specific resource.
     *
     * @param userDetails the authenticated user details obtained from the security context
     * @param authorizationRequest the request containing details about the resource being accessed
     * @return a ResponseEntity containing a SapphireAPIResponse with the UserDto if the user is authorized,
     *         or an appropriate error message if access is forbidden
     */
    @Operation(
            method = "POST",
            description = "Get the User DTO if the user has access to the resource, else returns access forbidden",
            tags = {"resource"}
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully authorized the user",
                            content = {
                                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = UserDto.class))
                            }
                    )
            }
    )
    @PostMapping("/validate")
    ResponseEntity<SapphireAPIResponse<UserDto>> validateResourceAccess(@AuthenticationPrincipal UserDetails userDetails,
                                                                        @Valid @RequestBody AuthorizationRequest authorizationRequest);
}
