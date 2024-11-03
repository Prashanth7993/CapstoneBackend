package com.capstone.bus_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capstone.bus_service.entity.RealTimeData;

@Repository
public interface RealTimeDataRepository extends JpaRepository<RealTimeData, Long> {
	List<RealTimeData> findByBusId(Integer busId);
}
