package com.capstone.bus_service.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.capstone.bus_service.entity.RealTimeData;

@Service
public class LocationConsumer {

    private RealTimeData latestLocation;

    @KafkaListener(topics = "bus-location-updates", groupId = "location-group")
    public void consumeLocation(RealTimeData location) {
        latestLocation = location;
    }

    public RealTimeData getLatestLocation() {
        return latestLocation;
    }
}
