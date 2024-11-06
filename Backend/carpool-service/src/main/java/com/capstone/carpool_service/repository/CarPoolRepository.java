package com.capstone.carpool_service.repository;

import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capstone.carpool_service.entity.CarPool;


@Repository
public interface CarPoolRepository extends JpaRepository<CarPool, Long> {
    List<CarPool> findByDriverId(Long driverId);
    List<CarPool> findByAvailableSeatsGreaterThan(int seats);
    List<CarPool> findByRouteId(long routeId);
}