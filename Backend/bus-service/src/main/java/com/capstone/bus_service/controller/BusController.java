package com.capstone.bus_service.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.capstone.bus_service.models.BusPojo;
import com.capstone.bus_service.entity.BusSchedule;
import com.capstone.bus_service.service.BusService;

@RestController
@RequestMapping("/buses")
public class BusController {
    
    @Autowired
    private BusService busService;

    @GetMapping
    public ResponseEntity<List<BusPojo>> getAllBuses() {
        List<BusPojo> buses = busService.getAllBuses();
        return new ResponseEntity<>(buses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BusPojo> getBusById(@PathVariable long id) {
        BusPojo bus = busService.getBusById(id);
        if (bus != null) {
            return new ResponseEntity<>(bus, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> createBus(@RequestBody BusPojo busPojo) {
        BusPojo createdBus = busService.createBus(busPojo);
        return new ResponseEntity<>(createdBus, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<BusPojo> updateBusStatus(@PathVariable long id, @RequestParam String status) {
        try {
            BusPojo updatedBus = busService.updateBusStatus(id, status);
            return ResponseEntity.ok(updatedBus);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping("/{busId}/schedules")
    public ResponseEntity<?> addScheduleToBus(@PathVariable long busId, @RequestBody BusSchedule schedule) {
        try {
            BusPojo bus = busService.addScheduleToBus(busId, schedule);
            return new ResponseEntity<>(bus, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @GetMapping("/routeIds")
    public ResponseEntity<List<BusPojo>> getAllBusesFromListOfRouteIds(@RequestParam List<Long> routeIds) {
        List<BusPojo> buses = busService.getAllBusesFromListOfRouteIds(routeIds);
        if (buses.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(buses);
    }
    
    @DeleteMapping("/{busId}")
    public ResponseEntity<?> deleteBusById(@PathVariable long busId){
    	busService.deleteBusById(busId);
    	return new ResponseEntity<>(Map.entry("message", "Successfully Deleted the bus"),HttpStatus.OK);
    }
}
