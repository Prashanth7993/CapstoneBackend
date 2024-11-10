package com.capstone.bus_service.models;

import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class BusSchedulePojo {

	private long id;

	private long routeId;

	private LocalTime departureTime;
	private LocalTime destinationArrivalTime;
	private boolean operatingStatus;

	private BusPojo bus;

}
