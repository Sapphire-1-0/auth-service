package com.brihaspathee.sapphire.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Null;
import lombok.*;

import java.util.Set;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 08, January 2025
 * Time: 5:27 AM
 * Project: sapphire
 * Package Name: com.brihaspathee.sapphire.model
 * To change this template use File | Settings | File and Code Template
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    /**
     * UserId of the user
     */
    @JsonProperty(required = false)
    @Schema(description = "User id of the user",
    example = "1001", requiredMode = Schema.RequiredMode.NOT_REQUIRED,
    accessMode = Schema.AccessMode.READ_ONLY)
    @Null
    private Long userId;

    /**
     * The username of the user
     */
    @JsonProperty(required = true)
    @Schema(description = "The username of the user",
    example = "johndoe", requiredMode = Schema.RequiredMode.REQUIRED,
    accessMode = Schema.AccessMode.READ_WRITE)
    private String username;

    /**
     * The password of the user
     */
    @JsonProperty(required = true)
    @Schema(description = "The password of the user",
    example = "$sef2s4%%@%%#@#", requiredMode = Schema.RequiredMode.REQUIRED,
    accessMode = Schema.AccessMode.READ_WRITE)
    private String password;

    /**
     * The roles associated with the user
     */
    @JsonProperty(required = true)
    @Schema(description = "The roles that are associated with the user")
    private Set<RoleDto> roles;

    /**
     * toString method
     * @return - String version of the user object
     */
    @Override
    public String toString() {
        return "UserDto{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                '}';
    }
}
