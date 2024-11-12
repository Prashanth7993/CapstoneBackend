//package com.capstone.bus_service.controller;
//
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.capstone.bus_service.entity.RealTimeData;
//import com.capstone.bus_service.kafka.LocationProducer;
//
//@RestController
//public class OperatorLocationController {
//
//	private LocationProducer locationProducer;
//
//	public  OperatorLocationController(LocationProducer locationProducer) {
//	        this.locationProducer = locationProducer;
//	    }
//
//	@PostMapping("/api/location/update")
//	public void updateLocation(@RequestBody RealTimeData location) {
//		System.out.println("producing location");
//		System.out.println(location);
//		locationProducer.sendLocationUpdate(location);
//	}
//}
