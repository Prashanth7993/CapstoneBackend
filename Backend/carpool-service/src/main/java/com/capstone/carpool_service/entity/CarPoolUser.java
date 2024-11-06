package com.capstone.carpool_service.entity;

import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "carpool_users")
@EqualsAndHashCode(exclude = "carpool")
public class CarPoolUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Long userId;
    private LocalTime requestTime;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "carpool_id")
    @JsonIgnoreProperties("carpoolUsers")
    private CarPool carpool;
}
