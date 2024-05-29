package com.acme.eazyschool.rest;

import com.acme.eazyschool.model.Response;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// General error handling controller
@Slf4j
@RestControllerAdvice(annotations = RestController.class)
@Order(1)                       // Execute before the global exception controller
public class GlobalExceptionRestController {

    // Handle exceptions related to bad requests
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response> handleValidationErrors(MethodArgumentNotValidException ex, HttpServletRequest request) {
        Response response = new Response("500", ex.getBindingResult().toString());
        // Accessing headers from the request
        String authorizationHeader = request.getHeader("Authorization");
        String contentTypeHeader = request.getHeader("Content-Type");
        log.info(authorizationHeader);
        log.info(contentTypeHeader);
        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }

    // Handle internally generated errors everywhere within the app
    @ExceptionHandler
    public ResponseEntity<Response> exceptionHandler(Exception e) {
        Response response = new Response("500", e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}