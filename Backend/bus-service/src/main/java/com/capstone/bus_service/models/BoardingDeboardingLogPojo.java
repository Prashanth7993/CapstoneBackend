package com.capstone.bus_service.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class BoardingDeboardingLogPojo {

	private long id;

	private long userId;
	private long busId;
	private LocalDateTime boardedTime;
	private LocalDateTime deboardedTime;

	private String status;
	private String comments;
}
