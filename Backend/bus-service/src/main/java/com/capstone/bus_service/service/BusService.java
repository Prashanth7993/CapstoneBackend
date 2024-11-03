package com.capstone.bus_service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.bus_service.entity.Bus;
import com.capstone.bus_service.entity.BusSchedule;
import com.capstone.bus_service.repository.BusRepository;

import jakarta.transaction.Transactional;

@Service
public class BusService {

	@Autowired
	private BusRepository busRepository;

	public List<Bus> getAllBuses() {
		return busRepository.findAll();
	}

	public Optional<Bus> getBusById(long id) {
		return busRepository.findById(id);
	}

	public Bus createBus(Bus bus) {
		return busRepository.save(bus);
	}

	@Transactional
	public Bus updateBusStatus(long busId, String newStatus) {
		return busRepository.findById(busId).map(bus -> {
			bus.setStatus(newStatus);
			return busRepository.save(bus);
		}).orElseThrow(() -> new RuntimeException("Bus not found"));
	}
	
	public Bus addScheduleToBus(long busId, BusSchedule schedule) throws Exception {
        Bus bus = getBusById(busId).get();
        if (bus != null) {
            bus.getBusSchedules().add(schedule);
            return busRepository.save(bus);
        } else {
            throw new IllegalArgumentException("Bus not found with id: " + busId);
        }
    }
	
	public List<Bus> getAllBusesFromListOfRouteIds(List<Long> routeIds) {
        return busRepository.findByRouteIdIn(routeIds);
    }
}