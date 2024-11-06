package com.capstone.feedback_service.controller;

import com.capstone.feedback_service.models.FeedbackPojo;
import com.capstone.feedback_service.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;


    @PostMapping("/submit/{serviceId}/user/{userId}")
    public ResponseEntity<FeedbackPojo> submitFeedback(@PathVariable long userId,@PathVariable long serviceId,
            @RequestBody FeedbackPojo feedback) {
        FeedbackPojo feedbackPojo = feedbackService.submitFeedback(userId,serviceId,feedback);
        return ResponseEntity.ok(feedbackPojo);
    }

    
    @GetMapping("/service/{serviceType}/{serviceId}")
    public ResponseEntity<List<FeedbackPojo>> getFeedback(
            @PathVariable String serviceType,
            @PathVariable long serviceId) {
        List<FeedbackPojo> feedbackList = feedbackService.getFeedback(serviceType, serviceId);
        return ResponseEntity.ok(feedbackList);
    }
}
