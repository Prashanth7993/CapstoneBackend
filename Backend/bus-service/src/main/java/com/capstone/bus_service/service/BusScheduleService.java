package com.capstone.bus_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.bus_service.entity.BusSchedule;
import com.capstone.bus_service.repository.BusRepository;
import com.capstone.bus_service.repository.BusScheduleRepository;

@Service
public class BusScheduleService {
    @Autowired
    private BusScheduleRepository busScheduleRepository;

    @Autowired
    private BusRepository busRepository;

    public List<BusSchedule> getAllSchedules() {
        return busScheduleRepository.findAll();
    }

    public List<BusSchedule> getSchedulesByBusId(int busId) {
        return busScheduleRepository.findByBusId(busId);
    }

    public BusSchedule createBusSchedule(BusSchedule schedule) {
        return busScheduleRepository.save(schedule);
    }
}