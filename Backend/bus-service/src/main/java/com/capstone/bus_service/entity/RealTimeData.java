package com.capstone.bus_service.entity;

import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
public class RealTimeData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @ManyToOne
    @JoinColumn(name = "bus_id")
    private Bus bus;
    private double latitude;
    private double longitude;
//    private String location;
    private LocalTime timestamp;
    private long occupancy;
    private String nextStop;
    
    
//    @PrePersist
//    public void updateDate() {
//    	this.timestamp=LocalTime.now();
//    }
}