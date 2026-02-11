package com.example.ecom_project.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
@RestControllerAdvice
public class GlobalExceptionHandler {

    //exception handling method
    @ExceptionHandler({ProductNotFound.class,IllegalArgumentException.class, NullPointerException.class})
    public ResponseEntity<Map<String, String>> handleex(Exception exception){
        Map<String, String> errorResponse = new HashMap<>() ;
        errorResponse.put("message",exception.getMessage());
        errorResponse.put("timestamp", ""+ LocalDateTime.now());
        errorResponse.put("error","bad request");
        errorResponse.put("status",""+ HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<Map<String, String>> handleMethodNotSupported(Exception exception){
        Map<String, String> errorResponse = new HashMap<>() ;
        errorResponse.put("message",exception.getMessage());
        errorResponse.put("timestamp", ""+ LocalDateTime.now());
        errorResponse.put("error","Method not allowed on this end point");
        errorResponse.put("status",""+ HttpStatus.METHOD_NOT_ALLOWED.value());
        return new ResponseEntity<>(errorResponse,HttpStatus.METHOD_NOT_ALLOWED);

    }


}
