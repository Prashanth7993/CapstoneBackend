package com.capstone.bus_service.service;

import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.bus_service.entity.BoardingDeboardingLog;
import com.capstone.bus_service.entity.Passenger; // Assuming this is the original entity
import com.capstone.bus_service.models.PassengerPojo;
import com.capstone.bus_service.repository.BoardingDeboardingLogRepository;
import com.capstone.bus_service.repository.PassengerRepository;

@Service
public class PassengerService {

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private BoardingDeboardingLogRepository logRepository;

    public PassengerPojo boardPassenger(long busId, long userId, long boardingStopId) {
        Passenger passenger = new Passenger();
        passenger.setBusId(busId);
        passenger.setUserId(userId);
        passenger.setBoardingStopId(boardingStopId);
        passenger.setBoardingTime(LocalDateTime.now());

        Passenger savedPassenger = passengerRepository.save(passenger);
        return convertToPojo(savedPassenger);
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

    private PassengerPojo convertToPojo(Passenger passenger) {
        PassengerPojo passengerPojo = new PassengerPojo();
        BeanUtils.copyProperties(passenger, passengerPojo);
        return passengerPojo;
    }
}
