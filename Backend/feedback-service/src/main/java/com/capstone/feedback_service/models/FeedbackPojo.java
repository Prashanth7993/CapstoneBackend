package com.capstone.feedback_service.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data


public class FeedbackPojo {
    private long id;

    private long userId;
    private String serviceType;
    private long serviceId; 
    private int rating; 
    private String comment; 
    private LocalDateTime timestamp; 
}