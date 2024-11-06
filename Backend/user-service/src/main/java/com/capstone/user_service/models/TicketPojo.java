package com.capstone.user_service.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class TicketPojo {

	private long id;
	private long busId;
	private long routeId;
	private LocalDateTime dateOfBooking;
	private LocalDateTime dateOfJourney;

	private UsersPojo user;

	private String seatNumber;
	private double price;
	private String status;
}
