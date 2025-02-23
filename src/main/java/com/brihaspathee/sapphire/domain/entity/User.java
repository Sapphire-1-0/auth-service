package com.brihaspathee.sapphire.domain.entity;

import lombok.*;
import org.springframework.data.neo4j.core.schema.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 04, January 2025
 * Time: 1:50 PM
 * Project: sapphire
 * Package Name: com.brihaspathee.sapphire.domain.entity
 * To change this template use File | Settings | File and Code Template
 */
@Node("User")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {

    /**
     * The primary key of the user node
     */
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private Long userId;

    /**
     * The username of the user
     */
    @Property(name = "username")
    private String username;

    /**
     * The password of the user
     */
    @Property(name = "password")
    private String password;

    /**
     * Represents the unique identifier for the service associated with the user.
     * This field typically links the user to a specific service instance or
     * operational context within the application.
     */
    @Property(name = "serviceId")
    private String serviceId;

    /**
     * Represents the type of the account associated with the user.
     * It may categorize the user account based on specific roles,
     * privileges, or operational distinctions.
     */
    @Property(name = "accountType")
    private String accountType;

    /**
     * Indicates if the account is expired
     */
    @Property(name = "accountNotExpired")
    private boolean accountNotExpired = true;

    /**
     * Indicates if the account is locked
     */
    @Property(name = "accountNotLocked")
    private boolean accountNotLocked = true;

    /**
     * Indicates if the credentials have expired
     */
    @Property(name = "credentialsNotExpired")
    private boolean credentialsNotExpired = true;

    /**
     * Indicates if the account is enabled or disabled
     */
    @Property(name = "enabled")
    private boolean enabled = true;

    /**
     * Set of roles assigned to the user
     */
    @Relationship(type = "HAS_ROLE", direction = Relationship.Direction.OUTGOING)
    private Set<Role> roles;

    /**
     * Authorities granted to the user
     * @return - return the list of authorities
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(Role::getAuthorities)
                .flatMap(Set::stream)
                .map(authority -> {
                    return new SimpleGrantedAuthority(authority.getPermission());
                }).collect(Collectors.toSet());
    }

    /**
     * Return the password of the user
     * @return - return the password of the user
     */
    @Override
    public String getPassword() {
        return this.password;
    }

    /**
     * Return the username of the user
     * @return - username of the user
     */
    @Override
    public String getUsername() {
        return this.username;
    }

    /**
     * Return the account not expired property
     * @return - the account not expired property
     */
    @Override
    public boolean isAccountNonExpired() {
        return this.accountNotExpired;
    }

    /**
     * Return the account not locked property
     * @return - account not locked property
     */
    @Override
    public boolean isAccountNonLocked() {
        return this.accountNotLocked;
    }

    /**
     * Return the credentials not expired property
     * @return - credentials not expired property
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNotExpired;
    }

    /**
     * Return the enabled property
     * @return - enabled propertu
     */
    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
