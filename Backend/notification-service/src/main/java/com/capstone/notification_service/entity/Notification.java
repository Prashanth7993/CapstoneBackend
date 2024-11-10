package com.capstone.notification_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity
public class Notification {
	
	@Id
	private long id;
	private long userId;
	private String type;
	private String message;
	private boolean isRead;

}
