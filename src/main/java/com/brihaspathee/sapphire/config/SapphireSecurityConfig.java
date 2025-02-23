package com.brihaspathee.sapphire.config;

import com.brihaspathee.sapphire.auth.SapphireUserDetailsService;
import com.brihaspathee.sapphire.auth.filter.SapphireAuthenticationFilter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 05, January 2025
 * Time: 6:41 AM
 * Project: sapphire
 * Package Name: com.brihaspathee.sapphire.config
 * To change this template use File | Settings | File and Code Template
 */
@Configuration
@EnableWebSecurity
//@EnableMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SapphireSecurityConfig {

    /**
     * The instance of the custom user details service
     */
    private final SapphireUserDetailsService sapphireUserDetailsService;

    /**
     * The `sapphireAuthenticationFilter` is an instance of the `SapphireAuthenticationFilter` class.
     * It acts as a custom security filter for handling JWT-based authentication in the security configuration.
     * This filter intercepts incoming HTTP requests, validates the JWT token provided in the Authorization header,
     * and sets the appropriate authentication details in the Spring Security context.
     *
     * Key Responsibilities:
     * - Extract & validate JWT tokens from incoming requests.
     * - Load user details using*/
    private final SapphireAuthenticationFilter sapphireAuthenticationFilter;

    /**
     * Constructs a new instance of SapphireSecurityConfig.
     *
     * @param sapphireUserDetailsService the service responsible for loading user details by username, typically for authentication-related operations.
     * @param sapphireAuthenticationFilter the custom filter used for processing and validating authentication requests, such as handling JWT tokens.
     */
    public SapphireSecurityConfig(SapphireUserDetailsService sapphireUserDetailsService,
                                  SapphireAuthenticationFilter sapphireAuthenticationFilter) {
        this.sapphireUserDetailsService = sapphireUserDetailsService;
        this.sapphireAuthenticationFilter = sapphireAuthenticationFilter;
    }

    /**
     * List of URLs for which authentication should be disabled
     */
    private static final String[] AUTH_WHITELIST = {
            "/swagger-resources/**",

            // -- Swagger UI v3 (OpenAPI)
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/h2-console/**",
            "/host",
            "/swagger-ui.html",
            "/v3/api-docs.yaml",
            "/api/v1/sapphire/auth/public/**",
            // other public endpoints of your API may be appended to this array
//            "/sapphire/jwt/authenticate",
//            "/api/v1/sapphire/welcome",
//            "/api/v1/sapphire/login",
//            "/api/v1/sapphire/register",
    };

    /**
     * The security filter chain method
     * @param http - The http security
     * @return - return the security filter chain
     * @throws Exception - Any exception that is generated during the process
     */
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.sessionManagement(session -> session.sessionCreationPolicy(
                SessionCreationPolicy.STATELESS))
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(AUTH_WHITELIST).permitAll()
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .anyRequest().authenticated());
//                .authorizeHttpRequests(auth -> auth.requestMatchers(AUTH_WHITELIST)
//                        .authenticated()
//                        .anyRequest()
//                        .permitAll())
//                .userDetailsService(sapphireUserDetailsService);
//                .httpBasic(Customizer.withDefaults());
        http.addFilterBefore(sapphireAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    /**
     * BCrypt password encoder is used to encode password of the users
     * @return - BCrypt password encoder instance
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    protected AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
