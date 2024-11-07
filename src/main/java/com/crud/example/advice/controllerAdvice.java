package com.crud.example.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class controllerAdvice{


    @ExceptionHandler({Exception.class})
    public ResponseEntity<?> handleNullPointerException(NullPointerException ex){
        return new ResponseEntity<String>("Example Null pointer from advice", HttpStatus.BAD_REQUEST);
    }



}
