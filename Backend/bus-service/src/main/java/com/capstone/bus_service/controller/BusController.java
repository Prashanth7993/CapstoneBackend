package com.capstone.bus_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.bus_service.entity.Bus;
import com.capstone.bus_service.entity.BusSchedule;
import com.capstone.bus_service.service.BusService;

@RestController
@RequestMapping("/buses")
public class BusController {
	@Autowired
    private BusService busService;

    @GetMapping
    public ResponseEntity<?> getAllBuses() {
        return new ResponseEntity<>(busService.getAllBuses(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBusById(@PathVariable Integer id) {
        Bus bus = busService.getBusById(id).get();
        if (bus != null) {
            return new ResponseEntity<>(bus, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Bus> createBus(@RequestBody Bus bus) {
        Bus createdBus = busService.createBus(bus);
        return new ResponseEntity<>(createdBus, HttpStatus.OK);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<?> updateBusStatus(@PathVariable Integer id, @RequestParam String status) {
        try {
            Bus updatedBus=busService.updateBusStatus(id, status);
            return ResponseEntity.status(200).body(updatedBus); 
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build(); 
        }
    }
    
    @PostMapping("/{busId}/schedules")
    public ResponseEntity<?> addScheduleToBus(@PathVariable long busId, @RequestBody BusSchedule schedule) {
        try {
			Bus bus = busService.addScheduleToBus(busId, schedule);
			return new ResponseEntity<>(bus,HttpStatus.OK);
		} catch (Exception e) {			
			e.printStackTrace();
			return ResponseEntity.badRequest().body(e.getMessage());
		}
    }
    
    @GetMapping("/routeIds")
    public ResponseEntity<?> getAllBusesFromListOfRouteIds(@RequestParam List<Long> routeIds) {
        List<Bus> buses = busService.getAllBusesFromListOfRouteIds(routeIds);
        if (buses.isEmpty()) {
            return ResponseEntity.noContent().build(); 
        }
        return ResponseEntity.ok(buses); 
    }
}