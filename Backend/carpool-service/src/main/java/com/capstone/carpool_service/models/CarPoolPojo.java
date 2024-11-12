package com.capstone.carpool_service.models;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import com.capstone.carpool_service.entity.CarPoolUser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class CarPoolPojo {

	private Long id;

	private Long driverId;
	private Long routeId;
	private int capacity;
	private int availableSeats;
	private LocalTime departureTime;
	private String pickupLocation;

	private Set<CarPoolUserPojo> carpoolUsers = new HashSet<>();

}
