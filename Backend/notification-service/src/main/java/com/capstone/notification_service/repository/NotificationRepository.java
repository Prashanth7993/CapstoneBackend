package com.capstone.notification_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capstone.notification_service.entity.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
	
	List<Notification> findByUserId(long userId);
	
	List<Notification> findByUserIdAndIsRead(long userId,boolean isRead);

}
