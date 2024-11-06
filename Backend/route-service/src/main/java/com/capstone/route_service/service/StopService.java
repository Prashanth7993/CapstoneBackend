package com.capstone.route_service.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.route_service.entity.Stop;
import com.capstone.route_service.models.StopPojo;
import com.capstone.route_service.repository.StopRepository;

@Service
public class StopService {

    @Autowired
    private StopRepository stopRepository;

    public List<StopPojo> findAllStops() {
        List<Stop> stops = stopRepository.findAll();
        return stops.stream()
                    .map(this::convertToPojo)
                    .collect(Collectors.toList());
    }

    public StopPojo findStopById(long id) {
        return stopRepository.findById(id)
                             .map(this::convertToPojo)
                             .orElse(null);
    }

    public StopPojo createStop(StopPojo stopPojo) {
        Stop stop = new Stop();
        BeanUtils.copyProperties(stopPojo, stop);
        Stop savedStop = stopRepository.save(stop);
        return convertToPojo(savedStop);
    }

    public StopPojo updateStop(StopPojo stopPojo) {
        Stop stop = new Stop();
        BeanUtils.copyProperties(stopPojo, stop);
        Stop updatedStop = stopRepository.save(stop);
        return convertToPojo(updatedStop);
    }

    public void deleteStop(long id) {
        stopRepository.deleteById(id);
    }

    private StopPojo convertToPojo(Stop stop) {
        StopPojo stopPojo = new StopPojo();
        BeanUtils.copyProperties(stop, stopPojo);
        return stopPojo;
    }
}
