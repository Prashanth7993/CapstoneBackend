package com.capstone.bus_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class BoardingDeboardingLog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long userId; 
    private long busId;
    private LocalDateTime boardedTime;
    private LocalDateTime deboardedTime;
    
    private String status; 
    private String comments; 
}
