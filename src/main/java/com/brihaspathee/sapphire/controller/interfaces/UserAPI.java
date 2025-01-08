package com.brihaspathee.sapphire.controller.interfaces;

import com.brihaspathee.sapphire.model.UserList;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 07, January 2025
 * Time: 5:40 AM
 * Project: sapphire
 * Package Name: com.brihaspathee.sapphire.controller.interfaces
 * To change this template use File | Settings | File and Code Template
 */
@RequestMapping("/api/v1/sapphire/auth/user")
@Validated
public interface UserAPI {

    /**
     * Get all the users that are present in the system
     * @return - return the list of all users
     */
    @Operation(
            method = "GET",
            description = "Get all the users in the system",
            tags = {"security"}
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved all the users",
                            content = {
                                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = UserList.class))
                            }
                    )
            }
    )
    @GetMapping
    ResponseEntity<UserList> getAllUsers();
}
