package com.example.batchmanagingsystem.error;

import lombok.Getter;

@Getter
public class AppError extends RuntimeException {

    final private String code;

    public AppError(String code, String message) {
        super(message);
        this.code = code;
    }
}
