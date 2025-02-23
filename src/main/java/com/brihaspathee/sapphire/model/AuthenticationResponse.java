package com.brihaspathee.sapphire.model;

import lombok.*;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 21, February 2025
 * Time: 8:55 AM
 * Project: sapphire
 * Package Name: com.brihaspathee.sapphire.model
 * To change this template use File | Settings | File and Code Template
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse {

    /**
     * Represents the access token issued to a user after successful authentication.
     * This token is used to authorize subsequent requests to secure endpoints
     * within the application. It should be securely stored and transmitted to ensure
     * the validity of the session and protect against unauthorized access.
     */
    private String accessToken;
}
