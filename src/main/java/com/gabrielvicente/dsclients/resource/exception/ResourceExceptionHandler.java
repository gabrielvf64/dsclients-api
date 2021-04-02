package com.gabrielvicente.dsclients.resource.exception;

import com.gabrielvicente.dsclients.service.exception.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<StandardError> handleEntityNotFoundException(EntityNotFoundException e, HttpServletRequest request) {
        StandardError standardError = new StandardError();
        HttpStatus notFound = HttpStatus.NOT_FOUND;
        standardError.setTimeStamp(Instant.now());
        standardError.setStatus(notFound.value());
        standardError.setError("Resource not found");
        standardError.setMessage(e.getMessage());
        standardError.setPath(request.getRequestURI());
        return ResponseEntity.status(notFound).body(standardError);
    }
}
