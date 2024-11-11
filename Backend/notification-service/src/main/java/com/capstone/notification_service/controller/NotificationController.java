package com.capstone.notification_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.notification_service.models.NotificationPojo;
import com.capstone.notification_service.service.NotificationService;

@RestController
@RequestMapping("/notifications")
public class NotificationController {
	
	@Autowired
	NotificationService service;
	
	
	@GetMapping
	public ResponseEntity<?> getAllNotifications(){
		return new ResponseEntity<>(service.getAllNotifications(),HttpStatus.OK);
	}
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<?> getAllNotificationsOfUser(@PathVariable long userId){
		return new ResponseEntity<>(service.getNotificationByUserId(userId),HttpStatus.OK);
	}
	
	@PostMapping("/user/{userId}")
	public ResponseEntity<?> addNewNotificationToUser(@PathVariable long userId,@RequestBody NotificationPojo notification){
		return new ResponseEntity<>(service.addNotification(userId, notification),HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteNotification(@PathVariable long id){
		boolean deleted=service.deleteNotification(id);
		return new ResponseEntity<>(deleted,HttpStatus.OK);
	}
	
}
