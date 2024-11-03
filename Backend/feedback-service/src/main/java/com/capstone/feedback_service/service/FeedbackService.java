package com.capstone.feedback_service.service;


import com.capstone.feedback_service.entity.Feedback;
import com.capstone.feedback_service.models.FeedbackPojo;
import com.capstone.feedback_service.repository.FeedbackRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    // Method to submit feedback
    public FeedbackPojo submitFeedback(long userId,long serviceId, FeedbackPojo feedback) {
        Feedback feedbackConverted=new Feedback();
        BeanUtils.copyProperties(feedback, feedbackConverted);
        feedbackConverted.setServiceId(serviceId);
        Feedback savedFeedback = feedbackRepository.save(feedbackConverted);
        
        FeedbackPojo feedbackPojo = new FeedbackPojo();
        BeanUtils.copyProperties(savedFeedback, feedbackPojo);
        return feedbackPojo;
    }

    // Method to retrieve feedback for a specific service
    public List<FeedbackPojo> getFeedback(String serviceType, long serviceId) {
        List<Feedback> feedbackList = feedbackRepository.findByServiceTypeAndServiceId(serviceType, serviceId);
        
        // Convert to List<FeedbackPojo>
        return feedbackList.stream().map(feedback -> {
            FeedbackPojo feedbackPojo = new FeedbackPojo();
            BeanUtils.copyProperties(feedback, feedbackPojo);
            return feedbackPojo;
        }).collect(Collectors.toList());
    }
}
