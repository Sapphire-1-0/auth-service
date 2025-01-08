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
    @Query("MATCH (u:User) WHERE u.username = $username RETURN u")
    Optional<User> findByUsername(String username);
}
