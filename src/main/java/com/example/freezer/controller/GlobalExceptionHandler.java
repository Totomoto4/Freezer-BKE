package com.example.freezer.controller;

import com.example.freezer.exception.AuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<String> handleAuthenticationException(){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró un usuario con ese email o contraseña");
    }

}
