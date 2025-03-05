package com.brihaspathee.sapphire.model;

import com.brihaspathee.sapphire.dto.auth.AuthorityDto;
import lombok.*;

import java.util.List;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 24, February 2025
 * Time: 4:43 PM
 * Project: sapphire
 * Package Name: com.brihaspathee.sapphire.model
 * To change this template use File | Settings | File and Code Template
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResourceDto {

    /**
     * Represents the unique identifier of a resource.
     * This field serves as a globally unique value to distinctly identify a resource within the system.
     * It is typically used to reference or retrieve resource-related information.
     */
    private String resourceId;

    /**
     * Represents the name of the resource.
     * This field specifies the name or title assigned to the resource, which helps
     * in identifying and differentiating it from other resources within the system.
     */
    private String resourceName;

    /**
     * Represents the type of the resource.
     * This field defines the specific category or classification of the resource,
     * which helps in identifying and managing resources based on their types.
     */
    private String resourceType;

    /**
     * Provides a description of the resource.
     * This field contains additional details or metadata about the resource,
     * which can include its purpose, characteristics, or any other relevant
     * information that offers context about the resource's usage or functionality.
     */
    private String resourceDescription;

    /**
     * Represents the URI of the resource.
     * This field contains the location or identifier of the resource
     * in the form of a Uniform Resource Identifier (URI), which
     * facilitates access or identification of the resource within
     * a network or system.
     */
    private String resourceUri;

    /**
     * Represents the owner of the resource.
     * This field typically contains information or an identifier of the entity
     * that owns or is responsible for the resource.
     */
    private String resourceOwner;

    /**
     * Represents the collection of authorities associated with the resource.
     * Each authority in the list defines specific permissions or access rights that
     * can be granted to users or roles interacting with the resource. This field is
     * critical for managing access control and enforcing security policies within the system.
     */
    private List<AuthorityDto> authorities;
}
