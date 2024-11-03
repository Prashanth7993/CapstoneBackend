package com.capstone.bus_service.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.capstone.bus_service.entity.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {
    Passenger findFirstByUserIdAndBusId(long userId, long busId);
}