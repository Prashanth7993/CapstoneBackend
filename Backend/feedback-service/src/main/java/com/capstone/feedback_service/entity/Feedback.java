package com.capstone.feedback_service.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long userId;
    private String serviceType;
    private long serviceId; 
    private int rating; 
    private String comment; 
    private LocalDateTime timestamp; 
    
    @PrePersist
    public void updateDateTime() {
    	this.timestamp=LocalDateTime.now();
    }
}