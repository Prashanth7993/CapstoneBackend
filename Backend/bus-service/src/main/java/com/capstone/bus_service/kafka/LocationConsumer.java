package com.capstone.bus_service.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.capstone.bus_service.entity.RealTimeData;

@Service
public class LocationConsumer {

    private String latestLocation;

    @KafkaListener(topics = "bus-location-updates", groupId = "location-group")
    public void consumeLocation(String location) {
        latestLocation = location;
    }

    public String getLatestLocation() {
        return latestLocation;
    }
}
