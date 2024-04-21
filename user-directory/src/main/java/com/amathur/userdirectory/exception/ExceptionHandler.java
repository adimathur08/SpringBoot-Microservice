package com.amathur.userdirectory.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ExceptionHandler
{
    public static ResponseEntity<Map<String, Object>> getExceptionResponse(Exception exception)
    {
        Map<String, Object> body = new HashMap<>();
        body.put("status", "400");
        Map<String, String> errors = new HashMap<>();
        errors.put("message", exception.getMessage());
        body.put("errors", errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }
}
