package com.capstone.bus_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.bus_service.models.RealTimeDataPojo;
import com.capstone.bus_service.service.RealTimeDataService;

@RestController
@RequestMapping("/realtime")
public class RealTimeDataController {

    @Autowired
    private RealTimeDataService realTimeDataService;

    @PostMapping("/{busId}")
    public ResponseEntity<RealTimeDataPojo> reportRealTimeData(@PathVariable int busId, @RequestBody RealTimeDataPojo data) {
        try {
            RealTimeDataPojo reportedData = realTimeDataService.reportRealTimeData(busId, data);
            return ResponseEntity.ok(reportedData);
        } catch (IllegalStateException e) {
        	e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/bus/{busId}")
    public ResponseEntity<List<RealTimeDataPojo>> getBusRealTimeData(@PathVariable Integer busId) {
        List<RealTimeDataPojo> realTimeDataList = realTimeDataService.getBusRealTimeData(busId);
        
        return ResponseEntity.ok(realTimeDataList);
    }
}
