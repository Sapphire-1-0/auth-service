package com.brihaspathee.sapphire.domain.repository;

import com.brihaspathee.sapphire.domain.entity.Resource;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 24, February 2025
 * Time: 4:49 PM
 * Project: sapphire
 * Package Name: com.brihaspathee.sapphire.domain.repository
 * To change this template use File | Settings | File and Code Template
 */
@Repository
public interface ResourceRepository extends Neo4jRepository<Resource, Long> {

    /**
     * Finds a resource by its unique resource URI and retrieves its associated relationships
     * with authority information if present.
     *
     * @param resourceURI the unique URI of the resource to be retrieved
     * @return an Optional containing the Resource with the related authority information
     *         if found, or an empty Optional if no such resource exists
     */
    @Query("MATCH (r:Resource)-[na:NEEDS_AUTHORITY]->(a:Authority) WHERE r.resourceURI = $resourceURI RETURN r, na, a")
    Optional<Resource> findByResourceURIWithAuthority(String resourceURI);

    /**
     * Retrieves a resource by its unique resourceId.
     *
     * @param resourceID the unique identifier of the resource to be retrieved
     * @return an Optional containing the Resource if it exists, or an empty Optional if not found
     */
    Optional<Resource> findResourceByResourceId(String resourceID);

    /**
     * Finds a resource by its unique resourceId and retrieves the associated relationships
     * with authority information if present.
     *
     * @param resourceId the unique identifier of the resource to be retrieved
     * @return an Optional containing the Resource with the related authority information
     *         if found, or an empty Optional if no such resource exists
     */
    @Query("MATCH (r:Resource)-[na:NEEDS_AUTHORITY]->(a:Authority) WHERE r.resourceId = $resourceId RETURN r, na, a")
    Optional<Resource> findByResourceId(String resourceId);

}
