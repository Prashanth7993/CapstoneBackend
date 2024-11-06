package com.capstone.bus_service.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.bus_service.entity.Bus;
import com.capstone.bus_service.entity.RealTimeData; // Assuming this is the original entity
import com.capstone.bus_service.models.RealTimeDataPojo;
import com.capstone.bus_service.repository.BusRepository;
import com.capstone.bus_service.repository.RealTimeDataRepository;

@Service
public class RealTimeDataService {
    @Autowired
    private RealTimeDataRepository realTimeDataRepository;

    @Autowired
    private BusRepository busRepository;

    public RealTimeDataPojo reportRealTimeData(long busId, RealTimeDataPojo dataPojo) {
        Bus bus = busRepository.findById(busId).orElse(null);
        if (bus != null && "Active".equalsIgnoreCase(bus.getStatus())) {
            RealTimeData data = new RealTimeData(); // Assuming this is the original entity
            BeanUtils.copyProperties(dataPojo, data);
            data.setBus(bus);
            RealTimeData savedData = realTimeDataRepository.save(data);
            return convertToPojo(savedData);
        } else {
            throw new IllegalStateException("Bus is either not found or not in active status");
        }
    }

    public List<RealTimeDataPojo> getBusRealTimeData(Integer busId) {
        List<RealTimeData> realTimeDataList = realTimeDataRepository.findByBusId(busId);
        return realTimeDataList.stream()
            .map(this::convertToPojo)
            .collect(Collectors.toList());
    }

    private RealTimeDataPojo convertToPojo(RealTimeData data) {
        RealTimeDataPojo pojo = new RealTimeDataPojo();
        BeanUtils.copyProperties(data, pojo);
        return pojo;
    }
}
