package com.example.customerservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ApiResponseError {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
}
