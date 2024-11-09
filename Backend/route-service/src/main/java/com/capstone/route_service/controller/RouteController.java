package com.capstone.route_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.route_service.models.RailwayStationPojo;
import com.capstone.route_service.models.RoutePojo;
import com.capstone.route_service.models.StopPojo;
import com.capstone.route_service.service.RouteService;

@RestController
@RequestMapping("/routes")
public class RouteController {

	@Autowired
	private RouteService routeService;

	@PostMapping
	public ResponseEntity<?> createRoute(@RequestBody RoutePojo routePojo) {
		RoutePojo createdRoute = routeService.createRoute(routePojo);
		return ResponseEntity.ok(createdRoute);
	}

	@GetMapping
	public ResponseEntity<?> getAllRoutes() {
		List<RoutePojo> routes = routeService.getAllRoutes();
		return ResponseEntity.ok(routes);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getRouteById(@PathVariable long id) {
		return routeService.getRouteById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@PostMapping("/{routeId}/stops")
	public ResponseEntity<?> addStop(@PathVariable long routeId, @RequestBody StopPojo stopPojo) {
		RoutePojo updatedRoute = routeService.addStopToRoute(routeId, stopPojo);
		return updatedRoute != null ? ResponseEntity.ok(updatedRoute) : ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{routeId}/stops/{stopId}")
	public ResponseEntity<?> removeStop(@PathVariable long routeId, @PathVariable long stopId) {
		RoutePojo updatedRoute = routeService.removeStopFromRoute(routeId, stopId);
		return updatedRoute != null ? ResponseEntity.ok(updatedRoute) : ResponseEntity.notFound().build();
	}

	@GetMapping("/find")
	public ResponseEntity<?> findRoutes(@RequestParam String source, @RequestParam String destination) {
		List<Long> routes = routeService.findRoutesBySourceAndDestination(source, destination);
		return ResponseEntity.ok(routes);
	}

	@PostMapping("/stations/{routeId}")
	public ResponseEntity<?> addStationToRoute(@PathVariable long routeId,
			@RequestBody RailwayStationPojo stationPojo) {
		return new ResponseEntity<>(routeService.addStationToRoute(routeId, stationPojo), HttpStatus.OK);
	}

	@GetMapping("/stations/{routeId}")
	public ResponseEntity<?> getAllStationsOfRoute(@PathVariable long routeId) {
		return new ResponseEntity<>(routeService.getAllStationsOfRoute(routeId), HttpStatus.OK);
	}

	@GetMapping("/count")
	public ResponseEntity<?> getCountOfRoutes() {
		return new ResponseEntity<>(routeService.getCountOfActiveRoutes(), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteRouteById(@PathVariable long id) {
		return new ResponseEntity<>(routeService.deleteRouteById(id), HttpStatus.OK);
	}
}
