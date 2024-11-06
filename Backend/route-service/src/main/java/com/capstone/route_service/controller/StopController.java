package com.capstone.route_service.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.route_service.entity.Route;
import com.capstone.route_service.models.RoutePojo;
import com.capstone.route_service.models.StopPojo;
import com.capstone.route_service.repository.RouteRepository;
import com.capstone.route_service.service.StopService;

@RestController
@RequestMapping("/stops")
public class StopController {

    @Autowired
    private StopService stopService;

    @Autowired
    private RouteRepository routeRepository;

    @GetMapping
    public ResponseEntity<List<StopPojo>> getAllStops() {
        List<StopPojo> stops = stopService.findAllStops();
        return ResponseEntity.ok(stops);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StopPojo> getStopById(@PathVariable long id) {
        StopPojo stop = stopService.findStopById(id);
        return (stop != null) ? ResponseEntity.ok(stop) : ResponseEntity.notFound().build();
    }

    @PostMapping("/{routeId}")
    public ResponseEntity<?> createStop(@RequestBody StopPojo stopPojo, @PathVariable long routeId) {
        Route routeFound = routeRepository.findById(routeId)
                .orElseThrow(() -> new RuntimeException("Route not found"));
        RoutePojo routePojo=new RoutePojo();
        BeanUtils.copyProperties(routeFound, routePojo);
        stopPojo.setRoute(routePojo); 
        StopPojo createdStop = stopService.createStop(stopPojo);
        return new ResponseEntity<>(createdStop, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StopPojo> updateStop(@PathVariable long id, @RequestBody StopPojo stopPojo) {
        stopPojo.setId(id); // Assuming you have a way to set the ID in the POJO
        StopPojo updatedStop = stopService.updateStop(stopPojo);
        return ResponseEntity.ok(updatedStop);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStop(@PathVariable long id) {
        stopService.deleteStop(id);
        return ResponseEntity.noContent().build();
    }
}
