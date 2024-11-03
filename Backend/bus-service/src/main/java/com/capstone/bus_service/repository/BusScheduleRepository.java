package com.capstone.bus_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capstone.bus_service.entity.BusSchedule;

public interface BusScheduleRepository extends JpaRepository<BusSchedule,Long> {
	List<BusSchedule> findByBusId(int busId);
}
