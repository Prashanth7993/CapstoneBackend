//package com.capstone.bus_service.controller;
//
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.capstone.bus_service.entity.RealTimeData;
//import com.capstone.bus_service.kafka.LocationConsumer;
//
//@RestController
//public class LocationController {
//
//    private final LocationConsumer locationConsumer;
//
//    public LocationController(LocationConsumer locationConsumer) {
//        this.locationConsumer = locationConsumer;
//    }
//
//    @GetMapping("/api/location/latest")
//    public String getLatestLocation() {
//        return locationConsumer.getLatestLocation();
//    }
//}
