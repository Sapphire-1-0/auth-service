package com.brihaspathee.sapphire.auth;

import com.brihaspathee.sapphire.domain.entity.User;
import com.brihaspathee.sapphire.domain.repository.UserRepository;
import com.brihaspathee.sapphire.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 05, January 2025
 * Time: 6:43 AM
 * Project: sapphire
 * Package Name: com.brihaspathee.sapphire.auth
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SapphireUserDetailsService implements UserDetailsService {

    /**
     * The user repository instance
     */
    private final UserRepository userRepository;

    /**
     * Load user by username from the database
     * @param username - the username of the user to be loaded from the database
     * @return - return the user details of the loaded user
     * @throws UsernameNotFoundException - exception that is generated if the user is not found
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UserNotFoundException {
        log.info("Getting the user from the Neo4j Database");
        User user = userRepository.findByUsername(username).orElseThrow(() -> {
            log.info("User with username {} not found", username);
            return new UserNotFoundException("User with username " + username + " not found");
        });
        log.info("Logged in user {}", user.getUsername());
        return user;
    }
}
