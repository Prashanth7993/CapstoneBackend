package com.capstone.carpool_service.models;

import java.time.LocalTime;

import com.capstone.carpool_service.entity.CarPool;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor

@Data
public class CarPoolUserPojo {
    private Long id;
    
    private Long userId;
    private LocalTime requestTime;
    
    private CarPoolPojo carpool;
}
