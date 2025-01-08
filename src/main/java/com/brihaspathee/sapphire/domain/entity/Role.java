package com.brihaspathee.sapphire.domain.entity;

import lombok.*;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.Set;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 04, January 2025
 * Time: 1:51 PM
 * Project: sapphire
 * Package Name: com.brihaspathee.sapphire.domain.entity
 * To change this template use File | Settings | File and Code Template
 */
@Node
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private Long roleId;

    private String roleName;

    @Relationship(type = "HAS_ROLE", direction = Relationship.Direction.INCOMING)
    private Set<User> users;

    @Relationship(type = "HAS_AUTHORITY", direction = Relationship.Direction.OUTGOING)
    private Set<Authority> authorities;
}
