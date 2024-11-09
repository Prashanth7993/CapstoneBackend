package com.capstone.bus_service.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.bus_service.entity.Bus;
import com.capstone.bus_service.entity.BusSchedule;
import com.capstone.bus_service.models.BusPojo;
import com.capstone.bus_service.models.BusSchedulePojo;
import com.capstone.bus_service.repository.BusRepository;
import com.capstone.bus_service.repository.BusScheduleRepository;

import jakarta.transaction.Transactional;

@Service
public class BusService {

    @Autowired
    private BusRepository busRepository;
    
    @Autowired
    private BusScheduleRepository busScheduleRepository;

    public List<BusPojo> getAllBuses() {
        List<Bus> buses = busRepository.findAll();
        
        List<BusPojo> pojoBuses = new ArrayList<>();
        buses.forEach(bus -> {
        	List<BusSchedule> schedules=bus.getBusSchedules();
        	List<BusSchedulePojo> schedulesPojo=new ArrayList<>();
        	schedules.stream().forEach(schedule->{
        		BusSchedulePojo pojo=new BusSchedulePojo();
        		BeanUtils.copyProperties(schedule, pojo);
        		schedulesPojo.add(pojo);
        	});
        	BusPojo pojoBus = convertToPojo(bus);
        	pojoBus.setBusSchedules(schedulesPojo);
            pojoBuses.add(pojoBus);
        });
        return pojoBuses;
    }

    public BusPojo getBusById(long id) {
    	Bus busFound=busRepository.findById(id).get();
    	BusPojo busPojo=new BusPojo();
    	BeanUtils.copyProperties(busFound, busPojo);
    	//converting schedules to schedule pojo
    	List<BusSchedule> schedulesFound=busFound.getBusSchedules();
    	System.out.println(schedulesFound);
    	List<BusSchedulePojo> pojoSchedules=new ArrayList<>();
    	schedulesFound.stream().forEach(schedule->{
    		BusSchedulePojo pojo=new BusSchedulePojo();
    		BeanUtils.copyProperties(schedule, pojo);
    		pojoSchedules.add(pojo);
    	});
    	
    	busPojo.setBusSchedules(pojoSchedules);
        return busPojo;
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
        schedule.setBus(bus);
        busScheduleRepository.saveAndFlush(schedule);
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
    
    public long getTheCountOfBus() {
    	return busRepository.count();
    }
    
    public long getOperatingBusCount() {
    	return busRepository.countByStatus("Operating");
    }
}
