package com.brihaspathee.sapphire.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Null;
import lombok.*;

import java.util.Set;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 1/8/25
 * Time: 5:55 PM
 * Project: sapphire
 * Package Name: com.brihaspathee.sapphire.model
 * To change this template use File | Settings | File and Code Template
 */
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {

    /**
     * The id of the role
     */
    @JsonProperty(required = false)
    @Schema(description = "The unique id of the role",
    example = "1001", requiredMode = Schema.RequiredMode.NOT_REQUIRED,
    accessMode = Schema.AccessMode.READ_ONLY)
    @Null
    private Long roleId;

    /**
     * The name of the role
     */
    @JsonProperty(required = true)
    @Schema(description = "The name of the role", example = "admin",
    requiredMode = Schema.RequiredMode.REQUIRED, accessMode = Schema.AccessMode.READ_WRITE)
    private String roleName;

    /**
     * The users to whom the role is assigned
     */
    private Set<UserDto> users;

    /**
     * The set of authorities assigned to the role
     */
    private Set<AuthorityDto> authorities;
}
