package com.sandbox.SpringApp.service;

import com.sandbox.SpringApp.dto.ErrorMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class ExceptionApiHandler {
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorMessage> notFoundException(ResponseStatusException exception) {
        return ResponseEntity
                .status(exception.getStatusCode())
                .body(new ErrorMessage(exception.getReason()));
    }
}
