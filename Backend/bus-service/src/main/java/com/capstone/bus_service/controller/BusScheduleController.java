package com.capstone.bus_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.bus_service.entity.BusSchedule;
import com.capstone.bus_service.service.BusScheduleService;

@RestController
@RequestMapping("/busSchedules")
public class BusScheduleController {
    @Autowired
    private BusScheduleService busScheduleService;

    @GetMapping
    public List<BusSchedule> getAllSchedules() {
        return busScheduleService.getAllSchedules();
    }

    @GetMapping("/bus/{busId}")
    public ResponseEntity<List<BusSchedule>> getSchedulesByBusId(@PathVariable int busId) {
        List<BusSchedule> schedules = busScheduleService.getSchedulesByBusId(busId);
        return ResponseEntity.ok(schedules);
    }

    @PostMapping
    public ResponseEntity<BusSchedule> createBusSchedule(@RequestBody BusSchedule schedule) {
        BusSchedule createdSchedule = busScheduleService.createBusSchedule(schedule);
        return ResponseEntity.ok(createdSchedule);
    }
}