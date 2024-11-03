package com.capstone.bus_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.bus_service.entity.Bus;
import com.capstone.bus_service.entity.RealTimeData;
import com.capstone.bus_service.repository.BusRepository;
import com.capstone.bus_service.repository.RealTimeDataRepository;

@Service
public class RealTimeDataService {
	@Autowired
	private RealTimeDataRepository realTimeDataRepository;

	@Autowired
	BusRepository busRepository;

	public RealTimeData reportRealTimeData(long busId, RealTimeData data) {
		Bus bus = busRepository.findById(busId).orElse(null);
		if (bus != null && "Active".equalsIgnoreCase(bus.getStatus())) {
			data.setBus(bus);
			return realTimeDataRepository.save(data);
		} else {
			throw new IllegalStateException("Bus is either not found or not in active status");
		}
	}

	public List<RealTimeData> getBusRealTimeData(Integer busId) {
		return realTimeDataRepository.findByBusId(busId);
	}
}