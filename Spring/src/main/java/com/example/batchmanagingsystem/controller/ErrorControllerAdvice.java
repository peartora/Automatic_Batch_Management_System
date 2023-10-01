package com.example.batchmanagingsystem.controller;

import com.example.batchmanagingsystem.dto.ErrorResponse;
import com.example.batchmanagingsystem.error.AppError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorControllerAdvice
{
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public void handleAllError() {
        return;
    }


    @ExceptionHandler(AppError.class)
    public ResponseEntity<ErrorResponse> handleAppError(AppError error) {
        final ErrorResponse body = ErrorResponse.builder().code(error.getCode()).message(error.getMessage()).build();
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}
