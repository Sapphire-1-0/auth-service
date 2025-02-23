package com.brihaspathee.sapphire.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 21, February 2025
 * Time: 8:52 AM
 * Project: sapphire
 * Package Name: com.brihaspathee.sapphire.model
 * To change this template use File | Settings | File and Code Template
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationRequest {

    /**
     * Represents the username required for authentication in the application.
     * This field must not be null, blank, or empty.
     * It has a minimum length of 3 characters and a maximum length of 50 characters.
     * It is used to uniquely identify a user within the system.
     */
    @NotNull
    @NotBlank
    @Size(min = 3, max = 50)
    private String username;

    /**
     * Represents the password required for authentication in the application.
     * This field must not be null, blank, or empty.
     * It has a minimum length of 6 characters and a maximum length of 100 characters.
     * It is used to securely authenticate users and enforce password complexity policies.
     */
    @NotNull
    @NotBlank
    @Size(min = 6, max = 100)
    private String password;
}
