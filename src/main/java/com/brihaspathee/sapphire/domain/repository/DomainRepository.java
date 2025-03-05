package com.brihaspathee.sapphire.domain.repository;

import com.brihaspathee.sapphire.domain.entity.Domain;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 28, February 2025
 * Time: 4:14 PM
 * Project: sapphire
 * Package Name: com.brihaspathee.sapphire.domain.repository
 * To change this template use File | Settings | File and Code Template
 */
@Repository
public interface DomainRepository extends Neo4jRepository<Domain, Long> {
}
