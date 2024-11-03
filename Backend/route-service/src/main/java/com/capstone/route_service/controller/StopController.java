package com.capstone.route_service.controller;

import java.util.List;

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
import com.capstone.route_service.entity.Stop;
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
	public ResponseEntity<List<Stop>> getAllStops() {
		List<Stop> stops = stopService.findAllStops();
		return ResponseEntity.ok(stops);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getStopById(@PathVariable long id) {
		Stop stop = stopService.findStopById(id);
		return (stop != null) ? ResponseEntity.ok(stop) : ResponseEntity.notFound().build();
	}

	@PostMapping("/{routeId}")
	public ResponseEntity<?> createStop(@RequestBody Stop stop, @PathVariable long routeId) {
		Route routeFound =  routeRepository.findById(routeId)
				.orElseThrow(() -> new RuntimeException("Route not found"));
		if (routeFound != null) {
			stop.setRoute(routeFound);
			Stop createdStop = stopService.createStop(stop);
			return new ResponseEntity<>(createdStop, HttpStatus.OK);
		}
		return ResponseEntity.badRequest().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateStop(@PathVariable long id, @RequestBody Stop stop) {
		stop.setId(id);
		Stop updatedStop = stopService.updateStop(stop);
		return ResponseEntity.ok(updatedStop);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteStop(@PathVariable long id) {
		stopService.deleteStop(id);
		return ResponseEntity.noContent().build();
	}
}
