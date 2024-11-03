package com.capstone.bus_service.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.bus_service.entity.BoardingDeboardingLog;
import com.capstone.bus_service.entity.Passenger;
import com.capstone.bus_service.repository.BoardingDeboardingLogRepository;
import com.capstone.bus_service.repository.PassengerRepository;

@Service
public class PassengerService {

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private BoardingDeboardingLogRepository logRepository;

    
    public Passenger boardPassenger(long busId, long userId, long boardingStopId) {
        Passenger passenger = new Passenger();
        passenger.setBusId(busId);
        passenger.setUserId(userId);
        passenger.setBoardingStopId(boardingStopId);
        passenger.setBoardingTime(LocalDateTime.now());

        return passengerRepository.save(passenger);
    }

    public BoardingDeboardingLog deboardPassenger(long userId, long busId) {
        Passenger passenger = passengerRepository.findFirstByUserIdAndBusId(userId, busId);
        if (passenger == null) {
            throw new RuntimeException("Passenger not found");
        }

        BoardingDeboardingLog log = new BoardingDeboardingLog();
        log.setUserId(userId);
        log.setBusId(busId);
        log.setBoardedTime(passenger.getBoardingTime());
        log.setDeboardedTime(LocalDateTime.now());

        logRepository.save(log);

 
        passengerRepository.delete(passenger);

        return log;
    }
}
