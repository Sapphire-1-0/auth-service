package com.brihaspathee.sapphire.domain.repository;

import com.brihaspathee.sapphire.domain.entity.User;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.Optional;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 05, January 2025
 * Time: 6:43 AM
 * Project: sapphire
 * Package Name: com.brihaspathee.sapphire.domain.repository
 * To change this template use File | Settings | File and Code Template
 */
public interface UserRepository extends Neo4jRepository<User, Long> {

    /**
     * Find user by username
     * @param username - the username of the user to be found
     * @return - return the user if present
     */
    @Query("MATCH (u:User)-[hr:HAS_ROLE]->(role:Role)-[ha:HAS_AUTHORITY]->(a:Authority)" +
            "            WHERE u.username = $username" +
            "            RETURN u, COLLECT (DISTINCT hr) AS has_role, COLLECT (DISTINCT ha) AS has_authority, COLLECT(DISTINCT role) AS roles, COLLECT(DISTINCT a) AS authorities")
    Optional<User> findByUsername(String username);

    /**
     * Retrieves a user by their username.
     *
     * @param username - the username of the user to be retrieved
     * @return an {@code Optional} containing the user if found, or an empty {@code Optional} if not found
     */
    Optional<User> findUserByUsername(String username);
}
