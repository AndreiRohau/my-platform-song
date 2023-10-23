package my.course.controller;

import my.course.exception.SongServiceException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class SongControllerAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler({ SongServiceException.class })
    public ResponseEntity<Object> handleResponseServiceException(SongServiceException ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), new HttpHeaders(), ex.getHttpStatus());
    }
}