package com.capstone.bus_service.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.bus_service.entity.BoardingDeboardingLog;
import com.capstone.bus_service.entity.Passenger;
import com.capstone.bus_service.service.PassengerService;

@RestController
@RequestMapping("/api/passengers")
public class PassengerController {

    @Autowired
    private PassengerService passengerService;

    @PostMapping("/board")
    public ResponseEntity<Passenger> boardPassenger(
            @RequestParam long busId,
            @RequestParam long userId,
            @RequestParam long boardingStopId) {
        Passenger passenger = passengerService.boardPassenger(busId, userId, boardingStopId);
        return ResponseEntity.ok(passenger);
    }

    @PostMapping("/deboard")
    public ResponseEntity<BoardingDeboardingLog> deboardPassenger(
            @RequestParam long userId,
            @RequestParam long busId) {
        BoardingDeboardingLog log = passengerService.deboardPassenger(userId, busId);
        return ResponseEntity.ok(log);
    }
}
