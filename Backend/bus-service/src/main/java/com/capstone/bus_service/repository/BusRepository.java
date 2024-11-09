package com.capstone.bus_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capstone.bus_service.entity.Bus;

@Repository
public interface BusRepository extends JpaRepository<Bus,Long> {
	 List<Bus> findByRouteIdIn(List<Long> routeIds);
	 
	 @Query("SELECT b FROM Bus b JOIN FETCH b.busSchedules")
	 List<Bus> findAllWithSchedules();
	 
	 Long countByStatus(String status);
}
