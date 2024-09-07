package org.rdutta.identitymanager.global;

import org.rdutta.identitymanager.error.ErrorDetailsResponse;
import org.rdutta.identitymanager.exceptions.EmployeeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler(EmployeeException.class)
    public ResponseEntity<ErrorDetailsResponse> handleError(EmployeeException e) {
        ErrorDetailsResponse errorDetails = ErrorDetailsResponse.builder()
                .errorCode(HttpStatus.BAD_REQUEST.value())
                .errorMessage(e.getMessage())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
    }
}
