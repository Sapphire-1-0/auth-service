package com.brihaspathee.sapphire.auth;

import com.brihaspathee.sapphire.exception.ApiException;
import com.brihaspathee.sapphire.web.response.SapphireAPIResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 25, February 2025
 * Time: 4:19 PM
 * Project: sapphire
 * Package Name: com.brihaspathee.sapphire.auth
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@Component
public class SapphireAuthenticationEntryPoint implements AuthenticationEntryPoint {

    /**
     * A thread-safe, singleton instance of ObjectMapper used for
     * serializing Java objects to JSON and deserializing JSON to Java objects.
     * Provides methods and utility functions to configure and handle
     * JSON processing within the application.
     */
    private static final ObjectMapper ObjectMapper = new ObjectMapper();

    /**
     * Handles the commencement of an authentication process when an authentication exception occurs.
     * This method is an entry point for handling unauthorized access or authentication failures
     * and generates appropriate responses based on the type of `AuthenticationException`.
     *
     * @param request the HTTP request object containing client request information
     * @param response the HTTP response object used to send the error response back to the client
     * @param authException the authentication exception that triggered the entry point
     * @throws IOException thrown if an I/O error occurs while writing to the response
     * @throws ServletException thrown if a general error related to the servlet occurs
     */
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException,
            ServletException {
        Throwable cause = authException.getCause();
        log.info("Inside the authentication entry point:{}", authException.getMessage(), authException);

        if(authException instanceof BadCredentialsException){
            generateResponse(response, "1000001", "Invalid Credentials Provided", HttpStatus.UNAUTHORIZED, HttpServletResponse.SC_UNAUTHORIZED,
                    "The username or password provided is incorrect",
                    "The username or password provided is incorrect",
                    "The username or password provided is incorrect");
        }else if(authException instanceof InsufficientAuthenticationException){
            generateResponse(response, "1000002", "Invalid JWT provided", HttpStatus.UNAUTHORIZED, HttpServletResponse.SC_UNAUTHORIZED,
                    "The JWT provided in the request header is not valid",
                    "Invalid JWT provided",
                    "Invalid JWT provided");
        }else{
            generateResponse(response, "1000003", "Authentication Exception occurred", HttpStatus.UNAUTHORIZED, HttpServletResponse.SC_UNAUTHORIZED,
                    "Authentication Exception occurred",
                    "Authentication Exception occurred",
                    "Authentication Exception occurred");
        }

    }

    /**
     * Generates an HTTP response with a JSON body that includes error details and status information.
     *
     * @param response the HttpServletResponse object to which the response will be written
     * @param errorCode the specific error code identifying the error type
     * @param errorMessage a description of the error that occurred
     * @param status the HTTP status object representing the status of the response
     * @param statusCode the HTTP status code to be included in the response
     * @param developerMessage a customized message intended for developers to understand the error
     * @param responseMessage the main response message to be included in the JSON response
     * @param responseReason the reason explaining the error or issue that occurred
     * @throws IOException if an input or output exception occurs while writing the response
     */
    private static void generateResponse(HttpServletResponse response,
                                         String errorCode,
                                         String errorMessage,
                                         HttpStatus status,
                                         int statusCode,
                                         String developerMessage,
                                         String responseMessage,
                                         String responseReason) throws IOException {
        log.error("Invalid JWT signature");
        ApiException apiException = ApiException.builder()
                .errorCode(errorCode)
                .errorMessage(errorMessage)
                .build();
        SapphireAPIResponse<ApiException> apiResponse = SapphireAPIResponse.<ApiException>builder()
                .statusCode(statusCode)
                .status(status)
                .response(apiException)
                .developerMessage(developerMessage)
                .message(responseMessage)
                .reason(responseReason)
                .build();
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write(ObjectMapper.writeValueAsString(apiResponse));
    }
}
