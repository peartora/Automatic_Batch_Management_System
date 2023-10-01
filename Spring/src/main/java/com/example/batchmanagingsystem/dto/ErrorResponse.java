package com.example.batchmanagingsystem.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorResponse
{
    final private boolean isError = true;
    final private String code;
    final private String message;
}
