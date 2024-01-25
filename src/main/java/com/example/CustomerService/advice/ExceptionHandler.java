package com.example.CustomerService.advice;

import com.example.CustomerService.exception.CustomerNotFoundException;
import com.example.CustomerService.exception.PasswordNotFoundEcxeption;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class ExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleInvalidParameters(org.springframework.web.bind.MethodArgumentNotValidException ex){
        Map<String, String> errorMap=new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error->{
            errorMap.put(error.getField(),error.getDefaultMessage());
        });
        return errorMap;
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleInvalidInput(HttpMessageNotReadableException e) {
        log.info("Invalid request payload.");
        return ResponseEntity.badRequest().body("Invalid request payload.");
    }

//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @org.springframework.web.bind.annotation.ExceptionHandler(HttpMessageNotReadableException.class)
//    public Map<String, String> handleEmptyParameters(org.springframework.web.bind.HttpMessageNotReadableException ex){
//        Map<String, String> errorMap=new HashMap<>();
//        ex.getBindingResult().getFieldErrors().forEach(error->{
//            errorMap.put(error.getField(),error.getDefaultMessage());
//        });
//        return errorMap;
//    }
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @org.springframework.web.bind.annotation.ExceptionHandler(CustomerNotFoundException.class)
    public Map<String,String> handleNameNotFoundError(CustomerNotFoundException ex){
        Map<String, String> errorMap=new HashMap<>();
        errorMap.put("errorMessage",ex.getMessage());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @org.springframework.web.bind.annotation.ExceptionHandler(PasswordNotFoundEcxeption.class)
    public Map<String,String> handlePasswordNotFoundError(PasswordNotFoundEcxeption ex){
        Map<String, String> errorMap=new HashMap<>();
        errorMap.put("errorMessage",ex.getMessage());
        return errorMap;
    }



}
