package com.capstone.carpool_service.entity;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import com.capstone.carpool_service.entity.CarPoolUser;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "carpools")
@EqualsAndHashCode(exclude = "carpoolUsers")
public class CarPool {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Long driverId;
    private Long routeId;
    private int capacity;
    private int availableSeats;
    private LocalTime departureTime;
    private String pickupLocation;
    
    @OneToMany(mappedBy = "carpool", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("carpool")  
    private Set<CarPoolUser> carpoolUsers = new HashSet<>();
    
    
}
