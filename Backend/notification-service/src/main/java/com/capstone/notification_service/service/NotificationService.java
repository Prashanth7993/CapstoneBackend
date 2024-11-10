package com.capstone.notification_service.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.notification_service.entity.Notification;
import com.capstone.notification_service.models.NotificationPojo;
import com.capstone.notification_service.repository.NotificationRepository;

@Service
public class NotificationService {
	
	@Autowired
	private NotificationRepository notificationRepository;
	
	
	public List<NotificationPojo> getAllNotifications(){
		List<Notification> notificationsFound=notificationRepository.findAll();
		return notificationsFound.stream().map(notification->{
			NotificationPojo pojo = new NotificationPojo();
			BeanUtils.copyProperties(notification, pojo);
			return pojo;
		}).collect(Collectors.toList());
	}
	
	public List<NotificationPojo> getNotificationByUserId(long userId) {
		List<Notification> notificationsFound=notificationRepository.findByUserId(userId);
		return notificationsFound.stream().map(notification->{
			NotificationPojo pojo = new NotificationPojo();
			BeanUtils.copyProperties(notification, pojo);
			return pojo;
		}).collect(Collectors.toList());
	}
	
	public List<NotificationPojo> getUnreadNotificationsOfUser(long userId){
		List<Notification> notificationsFound=notificationRepository.findByUserIdAndIsRead(userId, false);
		
		return notificationsFound.stream().map(notification->{
			NotificationPojo pojo = new NotificationPojo();
			BeanUtils.copyProperties(notification, pojo);
			return pojo;
		}).collect(Collectors.toList());
		
	}
	
	public NotificationPojo addNotification(long userId,NotificationPojo notificationPojo) {
		Notification notification=new Notification();
		BeanUtils.copyProperties(notificationPojo, notification);
		Notification createdNotification=notificationRepository.save(notification);
		BeanUtils.copyProperties(createdNotification, notificationPojo);
		return notificationPojo;
	}
	
	public boolean deleteNotification(long id) {
		notificationRepository.deleteById(id);
		return true;
	}

}
