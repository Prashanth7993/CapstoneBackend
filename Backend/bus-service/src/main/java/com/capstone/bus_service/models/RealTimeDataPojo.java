package com.capstone.bus_service.models;

import java.time.LocalTime;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class RealTimeDataPojo {
	private long id;
	private BusPojo bus;
	private String location;
	private LocalTime timestamp;
	private long occupancy;
}