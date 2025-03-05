package com.brihaspathee.sapphire.domain.entity;

import lombok.*;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 28, February 2025
 * Time: 3:51 PM
 * Project: sapphire
 * Package Name: com.brihaspathee.sapphire.domain.entity
 * To change this template use File | Settings | File and Code Template
 */
@Node("Domain")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Domain {

    /**
     * Represents the unique primary identifier for the entity in the database.
     * This field is automatically generated and is used as the primary key
     * to uniquely identify the entity instance.
     */
    @Id
    @GeneratedValue
    private Long id;

    /**
     * Represents the unique identifier of a domain.
     * This field is used to store a string that uniquely identifies
     * a domain within the system, ensuring differentiation
     * across multiple domain instances.
     */
    private String domainId;

    /**
     * Represents the name of the domain.
     * This field stores the unique name of a specific domain
     * and is used for identifying or referencing the domain
     * within the application.
     */
    private String domainName;

    /**
     * Provides a detailed description of the domain.
     * This field is intended to store human-readable information
     * that explains the purpose or nature of the domain.
     */
    private String domainDescription;

    /**
     * Represents a list of resources associated with a domain.
     * This field models the "HAS_RESOURCE" relationship in a Neo4j graph database,
     * indicating that the domain node is connected to multiple resource nodes.
     * The relationship is outgoing from the domain to the resources.
     */
    @Relationship(type = "HAS_RESOURCE", direction = Relationship.Direction.OUTGOING)
    private List<Resource> resources;
}
