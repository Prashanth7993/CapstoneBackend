package com.capstone.bus_service.models;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class BusSchedulePojo {

	private long id;

	private long routeId;

	private LocalDateTime departureTime;
	private LocalDateTime destinationArrivalTime;
	private boolean operatingStatus;

	private BusPojo bus;

}
