package com.brihaspathee.sapphire.controller.interfaces;

import com.brihaspathee.sapphire.web.dto.WelcomeDto;
import com.brihaspathee.sapphire.web.response.SapphireAPIResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 20, February 2025
 * Time: 3:28 PM
 * Project: sapphire
 * Package Name: com.brihaspathee.sapphire.controller.interfaces
 * To change this template use File | Settings | File and Code Template
 */
@RequestMapping("/api/v1/sapphire/auth/public")
@Validated
public interface WelcomeAPI {

    /**
     * Welcome endpoint to check for connectivity
     * @return - Welcome message
     */
    @GetMapping("/welcome")
    ResponseEntity<SapphireAPIResponse<WelcomeDto>> getWelcomeMessage();

}
