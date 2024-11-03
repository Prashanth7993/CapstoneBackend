package com.capstone.carpool_service.entity;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity
public class CarPool {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(nullable = false)
	private long driverId;

	@Column(nullable = false)
	private long routeId;

	@Column(nullable = false)
	private long capacity;

	@Column(nullable = false)
	private long availableSeats;

	@Column(nullable = false)
	private LocalTime departureTime;

	@Column(nullable = false)
	private String pickupLocation;
	
	private String status;

	@OneToMany(mappedBy = "carpool", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<CarPoolUser> users = new HashSet<>();

}
