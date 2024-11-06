package com.capstone.carpool_service.models;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ApiResponse<T> {
    private T data;
    private String message;
    private LocalDateTime timestamp = LocalDateTime.now();
    
    public ApiResponse(T data, String message) {
        this.data = data;
        this.message = message;
    }
}