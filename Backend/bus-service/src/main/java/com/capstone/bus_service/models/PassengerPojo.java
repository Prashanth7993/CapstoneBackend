package com.capstone.bus_service.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class PassengerPojo {
	private long id;
	private long busId;
	private long userId;
	private long boardingStopId;
	private long deboardingStopId;
	private LocalDateTime boardingTime;
	private LocalDateTime deboardingTime;
}
