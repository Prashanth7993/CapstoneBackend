package com.project.payment_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.project.payment_service.models.NotificationPojo;

@FeignClient(value="notification-service",url="http://notification-service:9092/notifications")
public interface NotificationClient {
	@PostMapping("/user/{userId}")
	public NotificationPojo addNewNotificationToUser(@PathVariable long userId,@RequestBody NotificationPojo notification);

}
