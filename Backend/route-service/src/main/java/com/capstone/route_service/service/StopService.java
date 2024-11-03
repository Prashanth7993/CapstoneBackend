package com.capstone.route_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.route_service.entity.Stop;
import com.capstone.route_service.repository.StopRepository;

@Service
public class StopService {

    @Autowired
    private StopRepository stopRepository;

    public List<Stop> findAllStops() {
        return stopRepository.findAll();
    }

    public Stop findStopById(long id) {
        return stopRepository.findById(id).orElse(null);
    }

    public Stop createStop(Stop stop) {
        return stopRepository.save(stop);
    }

    public Stop updateStop(Stop stop) {
        return stopRepository.save(stop);
    }

    public void deleteStop(long id) {
        stopRepository.deleteById(id);
    }
}
