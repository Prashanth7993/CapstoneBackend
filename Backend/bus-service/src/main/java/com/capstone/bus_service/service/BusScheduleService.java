package com.capstone.bus_service.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.bus_service.entity.BusSchedule;
import com.capstone.bus_service.models.BusSchedulePojo;
import com.capstone.bus_service.repository.BusRepository;
import com.capstone.bus_service.repository.BusScheduleRepository;

@Service
public class BusScheduleService {

    @Autowired
    private BusScheduleRepository busScheduleRepository;

    @Autowired
    private BusRepository busRepository;

    public List<BusSchedulePojo> getAllSchedules() {
        List<BusSchedule> schedules = busScheduleRepository.findAll();
        return schedules.stream()
                .map(this::convertToPojo)
                .collect(Collectors.toList());
    }

    public List<BusSchedulePojo> getSchedulesByBusId(long busId) {
        List<BusSchedule> schedules = busScheduleRepository.findByBusId(busId);
        return schedules.stream()
                .map(this::convertToPojo)
                .collect(Collectors.toList());
    }

    public BusSchedulePojo createBusSchedule(BusSchedulePojo schedulePojo) {
        BusSchedule schedule = new BusSchedule();
        BeanUtils.copyProperties(schedulePojo, schedule);
        BusSchedule savedSchedule = busScheduleRepository.save(schedule);
        return convertToPojo(savedSchedule);
    }

    private BusSchedulePojo convertToPojo(BusSchedule busSchedule) {
        BusSchedulePojo busSchedulePojo = new BusSchedulePojo();
        BeanUtils.copyProperties(busSchedule, busSchedulePojo);
        return busSchedulePojo;
    }
}
