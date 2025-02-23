package com.brihaspathee.sapphire.auth.service;

import com.brihaspathee.sapphire.domain.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.List;
import java.util.Map;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 20, February 2025
 * Time: 4:05 PM
 * Project: sapphire
 * Package Name: com.brihaspathee.sapphire.auth.service
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@Component
public class JwtService {

    /**
     * The SECRET_KEY used for signing or verifying JWT tokens.
     * This key ensures the integrity and authenticity of the tokens by providing
     * a secret string used during the encryption or hashing process.
     * It should be securely stored and not exposed publicly to prevent unauthorized access.
     */
    @Value("${application.security.jwt.secret-key}")
    private String secretKey;

    /**
     * Represents the expiration duration for JWT tokens in milliseconds.
     * This value is used to determine the validity period of generated tokens.
     * It is configured through an external property source and injected using the @Value annotation.
     */
    @Value("${application.security.jwt.expiration}")
    private long expiration;

    /**
     * Generates a JWT token for the given user.
     * The token includes claims such as user's username, user ID, and authorities,
     * along with an expiration time and issued date.
     *
     * @param user The user for whom the JWT token is being generated. The user object
     *             contains details such as username, user ID, and granted authorities.
     * @return A string representing the signed JWT token.
     */
    public String generateToken(User user) {
        log.info("Generating JWT token for user {}", user.getUsername());
        log.info("Expiration time for JWT token is {} milliseconds", expiration);
        log.info("Secret key for JWT token is {}", secretKey);
        List<String> authorities = user.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
//        Map<String, Object> claims = Map.of("authorities", authorities);
        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("authorities", authorities)
                .claim("username", user.getUsername())
                .claim("userId", user.getUserId())
//                .setClaims(claims)
                .setIssuedAt(java.util.Date.from(java.time.Instant.now()))
                .setExpiration(java.util.Date.from(java.time.Instant.now().plusMillis(expiration)))
                .signWith(getSigningKey())
                .compact();
    }

    /**
     * Validates the provided token by checking if it matches the username of the given user and if the token is not expired.
     *
     * @param token the token to be validated
     * @param user the user whose information will be used for validation
     * @return true if the token is valid and not expired, false otherwise
     */
    public boolean validateToken(String token, User user) {
        final String username = extractUsername(token);
        return (username.equals(user.getUsername()) && !isTokenExpired(token));
    }

    /**
     * Extracts the username from the provided token.
     * The username is retrieved from a claim within the token.
     *
     * @param token the token from which the username is to be extracted
     * @return the username extracted from the token
     */
    public String extractUsername(String token) {
        /*
          The username of the user is set in the subject and also set as a claim
          We are extracting the username from the claim.
          Refer to generateToken method in this class to see how the username is set
         */
        Claims claims = extractAllClaims(token);
        String username = claims.get("username", String.class);
        // String username = claims.getSubject();
        return username;
    }

    /**
     * Extracts a list of authorities from a given token.
     *
     * @param token the JWT token from which authorities are extracted
     * @return a list of {@code SimpleGrantedAuthority} objects representing the authorities in the token
     */
    public List<SimpleGrantedAuthority> extractAuthorities(String token) {
        /*
         * The authorities that are assigned to a user are set in the token as a claim with the key
         * "authorities". Refer to the method generateToken in this class to see how the authorities are set
         * in the token.
         */
        Claims claims = extractAllClaims(token);
        List<?> rawList = claims.get("authorities", List.class);
        List<String> authorities = rawList.stream().map(Object::toString).toList();
        return authorities.stream().map(SimpleGrantedAuthority::new).toList();
    }

    /**
     * Extracts the user ID from the given token. The user ID is stored as a claim with the key "userId".
     *
     * @param token the token from which the user ID needs to be extracted
     * @return the user ID extracted from the token, or null if the token does not contain a valid "userId" claim
     */
    public Long extractUserId(String token) {
        /*
         * The user id of the user is are set in the token as a claim with the key
         * "userId". Refer to the method generateToken in this class to see how the authorities are set
         * in the token.
         */
        Claims claims = extractAllClaims(token);
        Long userId = claims.get("userId", Long.class);
        // String username = claims.getSubject();
        return userId;
    }

    /**
     * Checks if the given token has expired.
     *
     * @param token the JWT token to be checked for expiration
     * @return true if the token is expired, false otherwise
     */
    public boolean isTokenExpired(String token) {
        Claims claims = extractAllClaims(token);
        return claims.getExpiration()
                .before(java.util.Date.from(java.time.Instant.now()));
    }



    /**
     * Extracts all claims from the provided JWT token.
     *
     * @param token the JWT token from which claims are to be extracted
     * @return the Claims object containing all claims present in the token
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Generates and retrieves a signing key for HMAC-SHA encryption using the secret key.
     *
     * @return A Key object used for signing or verifying JWT tokens.
     */
    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
