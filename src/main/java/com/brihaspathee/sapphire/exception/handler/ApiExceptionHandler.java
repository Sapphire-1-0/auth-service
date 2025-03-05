package com.brihaspathee.sapphire.exception.handler;

import com.brihaspathee.sapphire.exception.AccessDeniedException;
import com.brihaspathee.sapphire.exception.ApiException;
import com.brihaspathee.sapphire.exception.ApiExceptionList;
import com.brihaspathee.sapphire.exception.ResourceNotRegisteredException;
import com.brihaspathee.sapphire.web.response.SapphireAPIResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 25, February 2025
 * Time: 3:05 PM
 * Project: sapphire
 * Package Name: com.brihaspathee.sapphire.exception.handler
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@ControllerAdvice
public class ApiExceptionHandler {

    /**
     * Handles the ResourceNotRegisteredException thrown when a resource that is required
     * is not registered or available. This method provides a structured error response
     * to the client with appropriate status and error details.
     *
     * @param e the exception object of type ResourceNotRegisteredException containing
     *          details about the failure.
     * @return a ResponseEntity containing a SapphireAPIResponse object with an
     *         ApiExceptionList providing information about the exceptions, along with
     *         an HTTP BAD_REQUEST status.
     */
    @ExceptionHandler(ResourceNotRegisteredException.class)
    public ResponseEntity<SapphireAPIResponse<ApiExceptionList>> handleResourceNotRegisteredException(ResourceNotRegisteredException e) {
        log.info("Resource not registered exception: {}", e.getMessage());
        return getSapphireAPIResponseResponseEntity(e.getMessage(),
                "1000003",
                "The requested resource not available",
                "Resource not yet registered",
                e.getMessage(),
                HttpStatus.BAD_REQUEST,
                HttpStatus.BAD_REQUEST.value());
    }

    /**
     * Handles AccessDeniedException to provide a standardized error response.
     * This method is invoked whenever an AccessDeniedException is thrown in the application.
     *
     * @param e the AccessDeniedException instance, containing information about the exception
     * @return a ResponseEntity containing a SapphireAPIResponse with details about the error,
     *         including an error message, error code, description, and HTTP status
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<SapphireAPIResponse<ApiExceptionList>> handleAccessDeniedException(AccessDeniedException e) {
        log.info("Access Denied: {}", e.getMessage());
        return getSapphireAPIResponseResponseEntity(e.getMessage(),
                "1000004",
                "The user does not have access to the requested resource",
                "The user does not have access to the requested resource",
                e.getMessage(),
                HttpStatus.FORBIDDEN,
                HttpStatus.FORBIDDEN.value());
    }

    /**
     * Generates a ResponseEntity containing a structured SapphireAPIResponse object
     * with details about an API exception, including error details, status, and messages.
     *
     * @param errorMessage the message describing the error.
     * @param errorCode the specific code identifying the error type.
     * @param responseMessage the response message to be returned to the client.
     * @param responseReason the reason for the error occurrence.
     * @param developerMessage a message intended for developers for debugging purposes.
     * @param status the HTTP status to be set in the response.
     * @param statusCode the numeric HTTP status code to be included in the response.
     * @return a ResponseEntity containing a SapphireAPIResponse object encapsulating
     *         the error details and an HTTP BAD_REQUEST status.
     */
    private static ResponseEntity<SapphireAPIResponse<ApiExceptionList>> getSapphireAPIResponseResponseEntity(
            String errorMessage,
            String errorCode,
            String responseMessage,
            String responseReason,
            String developerMessage,
            HttpStatus status,
            int statusCode) {
        List<ApiException> apiExceptions = new ArrayList<>();
        ApiException apiException = ApiException.builder()
                .errorCode(errorCode)
                .errorMessage(errorMessage)
                .build();
        apiExceptions.add(apiException);
        ApiExceptionList exceptionList = ApiExceptionList.builder().apiExceptions(apiExceptions).build();
        SapphireAPIResponse<ApiExceptionList> apiResponse = SapphireAPIResponse.<ApiExceptionList>builder()
                .timestamp(LocalDateTime.now())
                .status(status)
                .message(responseMessage)
                .reason(responseReason)
                .developerMessage(developerMessage)
                .statusCode(statusCode)
                .response(exceptionList)
                .build();
        return new ResponseEntity<>(apiResponse, status);
    }
}
