package com.capstone.carpool_service.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class NotificationPojo {

	private long id;
	private long userId;
	private String type;
	private String message;
	private boolean isRead;

}
