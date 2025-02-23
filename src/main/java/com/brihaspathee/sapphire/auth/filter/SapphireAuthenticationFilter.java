package com.brihaspathee.sapphire.auth.filter;

import com.brihaspathee.sapphire.auth.SapphireUserDetailsService;
import com.brihaspathee.sapphire.auth.service.JwtService;
import com.brihaspathee.sapphire.domain.entity.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 20, February 2025
 * Time: 4:04 PM
 * Project: sapphire
 * Package Name: com.brihaspathee.sapphire.auth.filter
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class SapphireAuthenticationFilter extends OncePerRequestFilter {

    /**
     * An instance of SapphireUserDetailsService responsible for handling user-related operations in the authentication filter.
     * This service is primarily utilized to load user details by username during the authentication process.
     */
    private final SapphireUserDetailsService sapphireUserDetailsService;

    /**
     * An instance of JwtService responsible for handling operations related to JSON Web Tokens (JWT).
     * It provides functionality such as generating, validating, and extracting claims (e.g., username, user ID, authorities)
     * from JWT tokens. This service is used in the authentication process to ensure security and token integrity.
     */
    private final JwtService jwtService;

    /**
     * This method filters incoming requests to extract and validate JWT tokens, and sets
     * up user authentication in the SecurityContext if the token is valid and the user is authenticated.
     *
     * @param request the HttpServletRequest that contains the incoming request to be filtered.
     * @param response the HttpServletResponse that can be used to modify the response to the client.
     * @param filterChain the FilterChain object that allows the filter to pass the request
     *                    and response to the next filter in the chain.
     * @throws ServletException if an error occurs during the filtering process.
     * @throws IOException if an I/O error occurs during the filtering process.
     */
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        log.info("Filtering the incoming request");
        /*
            Get the authorization header
         */
        final String authorizationHeader = request.getHeader("Authorization");
        String jwt = null;
        String username = null;
        /*
            Get the jwt and the user details if the authorization header is not null
            and
            the value starts with Bearer
         */
        log.info("Authorization header: {}", authorizationHeader);
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            username = jwtService.extractUsername(jwt);
            log.info("Username: {}", username);
        }

        /*
            if the username is available in the token and there authentication object is
            not set in the security context
            Then get the details of the user from user details service
         */
        if(username != null &&
        SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.sapphireUserDetailsService.loadUserByUsername(username);
            log.info("User {} authenticated successfully", username);
            if(userDetails instanceof User){
                User user = (User) userDetails;
                /*
                    once the user is retrieved from the database, check if the jwt token in the
                    request is valid
                 */
                if(jwtService.validateToken(jwt, user)){
                    log.info("Token successfully validated for user {}", user.getUsername());
                    /*
                        If the request is valid then perform the below steps that would have
                        been done by Spring
                        1. Get all the authorities associated with the user
                        2. Create an instance of the UsernamePasswordAuthenticationToken with the user and their authorities
                        3. Set the incoming request into the authentication token
                        4. Set the authentication token in the Security context
                     */
                    List<SimpleGrantedAuthority> authorities = jwtService.extractAuthorities(jwt);
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(user, null,
                                    authorities);
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }else{
                    log.info("Token validation failed for user {}", user.getUsername());
                }
            }else{
                throw new BadCredentialsException("User not found");
            }
        }
        filterChain.doFilter(request, response);
    }
}
