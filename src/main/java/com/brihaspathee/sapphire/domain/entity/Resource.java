package com.brihaspathee.sapphire.domain.entity;

import lombok.*;
import org.springframework.data.neo4j.core.schema.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 24, February 2025
 * Time: 4:21 PM
 * Project: sapphire
 * Package Name: com.brihaspathee.sapphire.domain.entity
 * To change this template use File | Settings | File and Code Template
 */
@Node("Resource")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Resource {

    /**
     * Represents the unique identifier for the entity.
     * This field serves as the primary key for the entity, enabling
     * unique identification of each instance within the database or
     * any persistence layer being utilized.
     */
    @Id
    @GeneratedValue
    private Long id;

    /**
     * Represents the unique identifier of the resource.
     * This field is used to distinguish each resource individually
     * within the system, serving as a primary reference or key for
     * resource-related operations.
     */
    @Property("resourceId")
    private String resourceId;

    /**
     * Represents the name of the resource.
     * The resource name is a human-readable identifier used to describe or denote
     * the specific resource within the system. It serves to aid in distinguishing
     * the resource and providing context for its usage or purpose.
     */
    @Property("resourceName")
    private String resourceName;

    /**
     * Represents the type or category of the resource.
     * This field is used to classify the resource based on specific criteria,
     * allowing for organization, management, or distinction of resources within the system.
     */
    @Property("resourceType")
    private String resourceType;

    /**
     * Represents the URI of the resource.
     * The URI serves as a unique identifier or locator for the resource,
     * enabling it to be accessed or referenced within the system or across systems.
     */
    @Property("resourceURI")
    private String resourceURI;

    /**
     * Represents a textual description of the resource.
     * The description provides additional details or context about the
     * resource, enhancing its understandability or aiding in its identification.
     */
    @Property("resourceDescription")
    private String resourceDescription;

    /**
     * Represents the owner or entity responsible for the resource.
     * This field identifies the individual, system, or organization
     * that holds ownership or administrative responsibility over the resource.
     */
    @Property("resourceOwner")
    private String resourceOwner;

    /**
     * Represents the authority required or associated with the resource.
     * This relationship indicates that the resource has a dependency on
     * a specific authority, which defines the permissions or access
     * rights needed for operations related to the resource.
     */
    @Relationship(type = "NEEDS_AUTHORITY", direction = Relationship.Direction.OUTGOING)
    private List<Authority> authorities = new ArrayList<>();
}
