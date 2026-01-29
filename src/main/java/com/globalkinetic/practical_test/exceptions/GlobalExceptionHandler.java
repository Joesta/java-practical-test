package com.globalkinetic.practical_test.exceptions;

import com.globalkinetic.practical_test.dto.ErrorResponseDTO;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Joesta
 */

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<String> handleBadCredentials() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Username or password is invalid");
    }

    @ExceptionHandler(CredentialsRequiredException.class)
    public ResponseEntity<String> handleRequiredCredentials(CredentialsRequiredException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(e -> errors.put(e.getField(), e.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<String> handleAccessDenied(AccessDeniedException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
    }

//    @ExceptionHandler(ExpiredJwtException.class)
//    public ResponseEntity<ErrorResponseDTO> handleExpiredToken(ExpiredJwtException ex) {
//        ErrorResponseDTO err = new ErrorResponseDTO(
//                HttpStatus.UNAUTHORIZED.value(),
//                "Your session has expired. Please login again.",
//                Instant.now()
//        );
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(err);
//    }
}
