package my.platform.controller;

import my.platform.exception.ResourceServiceException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ResourceControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ ResourceServiceException.class })
    public ResponseEntity<Object> handleResourceServiceException(ResourceServiceException ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), new HttpHeaders(), ex.getHttpStatus());
    }
}