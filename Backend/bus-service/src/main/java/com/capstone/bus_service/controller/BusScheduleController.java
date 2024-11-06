package com.capstone.bus_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.capstone.bus_service.models.BusSchedulePojo;
import com.capstone.bus_service.service.BusScheduleService;

@RestController
@RequestMapping("/busSchedules")
public class BusScheduleController {

    @Autowired
    private BusScheduleService busScheduleService;

    @GetMapping
    public ResponseEntity<List<BusSchedulePojo>> getAllSchedules() {
        List<BusSchedulePojo> schedules = busScheduleService.getAllSchedules();
        return ResponseEntity.ok(schedules);
    }

    @GetMapping("/bus/{busId}")
    public ResponseEntity<List<BusSchedulePojo>> getSchedulesByBusId(@PathVariable long busId) {
        List<BusSchedulePojo> schedules = busScheduleService.getSchedulesByBusId(busId);
        return ResponseEntity.ok(schedules);
    }

    @PostMapping
    public ResponseEntity<BusSchedulePojo> createBusSchedule(@RequestBody BusSchedulePojo schedulePojo) {
        BusSchedulePojo createdSchedule = busScheduleService.createBusSchedule(schedulePojo);
        return ResponseEntity.ok(createdSchedule);
    }
}
