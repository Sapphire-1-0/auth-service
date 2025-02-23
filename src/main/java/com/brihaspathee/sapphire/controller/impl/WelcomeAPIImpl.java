package com.brihaspathee.sapphire.controller.impl;

import com.brihaspathee.sapphire.controller.interfaces.WelcomeAPI;
import com.brihaspathee.sapphire.web.dto.WelcomeDto;
import com.brihaspathee.sapphire.web.response.SapphireAPIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 20, February 2025
 * Time: 3:34 PM
 * Project: sapphire
 * Package Name: com.brihaspathee.sapphire.controller.impl
 * To change this template use File | Settings | File and Code Template
 */
@RestController
public class WelcomeAPIImpl implements WelcomeAPI {

    /**
     * Retrieves a welcome message to verify connectivity.
     *
     * @return ResponseEntity containing a SapphireAPIResponse with WelcomeDto data
     */
    @Override
    public ResponseEntity<SapphireAPIResponse<WelcomeDto>> getWelcomeMessage() {
        SapphireAPIResponse<WelcomeDto> response = SapphireAPIResponse.<WelcomeDto>builder()
                .response(WelcomeDto.builder()
                        .welcomeMessage("Hello! Welcome to Sapphire's authentication and authorization service! " +
                                "Your connectivity to this service is just fine")
                        .build())
                .developerMessage("Successfully fetched the welcome message")
                .statusCode(200)
                .message("Success")
                .reason("Success")
                .status(HttpStatus.OK)
                .build();
        return ResponseEntity.ok(response);
    }

}
