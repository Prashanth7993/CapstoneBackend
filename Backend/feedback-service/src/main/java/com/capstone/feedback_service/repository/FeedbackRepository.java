package com.capstone.feedback_service.repository;

import com.capstone.feedback_service.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    List<Feedback> findByServiceTypeAndServiceId(String serviceType, long serviceId);
}
