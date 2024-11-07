package com.capstone.bus_service.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.bus_service.entity.Bus;
import com.capstone.bus_service.entity.BusSchedule;
import com.capstone.bus_service.models.BusPojo;
import com.capstone.bus_service.repository.BusRepository;

import jakarta.transaction.Transactional;

@Service
public class BusService {

    @Autowired
    private BusRepository busRepository;

    public List<BusPojo> getAllBuses() {
        List<Bus> buses = busRepository.findAll();
        List<BusPojo> pojoBuses = new ArrayList<>();
        buses.forEach(bus -> {
            BusPojo pojoBus = convertToPojo(bus);
            pojoBuses.add(pojoBus);
        });
        return pojoBuses;
    }

    public BusPojo getBusById(long id) {
//    	Bus busFound=busRepository.fin
        return busRepository.findById(id)
            .map(this::convertToPojo)
            .orElse(null);
    }

    public BusPojo createBus(BusPojo bus) {
    	Bus busAdded=new Bus();
    	BeanUtils.copyProperties(bus, busAdded);
        Bus busCreated = busRepository.save(busAdded);
        return convertToPojo(busCreated);
    }

    @Transactional
    public BusPojo updateBusStatus(long busId, String newStatus) {
        return busRepository.findById(busId).map(bus -> {
            bus.setStatus(newStatus);
            Bus savedBus = busRepository.save(bus);
            return convertToPojo(savedBus);
        }).orElseThrow(() -> new RuntimeException("Bus not found"));
    }

    public BusPojo addScheduleToBus(long busId, BusSchedule schedule) throws Exception {
        Bus bus = busRepository.findById(busId)
            .orElseThrow(() -> new IllegalArgumentException("Bus not found with id: " + busId));
        
        bus.getBusSchedules().add(schedule);
        Bus savedBus = busRepository.save(bus);
        return convertToPojo(savedBus);
    }

    public List<BusPojo> getAllBusesFromListOfRouteIds(List<Long> routeIds) {
        List<Bus> buses = busRepository.findByRouteIdIn(routeIds);
        List<BusPojo> pojoBuses = new ArrayList<>();
        buses.forEach(bus -> {
            BusPojo pojoBus = convertToPojo(bus);
            pojoBuses.add(pojoBus);
        });
        return pojoBuses;
    }
    
    public boolean deleteBusById(long busId) {
    	busRepository.deleteById(busId);
    	return true;
    }

    private BusPojo convertToPojo(Bus bus) {
        BusPojo busPojo = new BusPojo();
        BeanUtils.copyProperties(bus, busPojo);
        return busPojo;
    }
}
