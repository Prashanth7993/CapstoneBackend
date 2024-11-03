package com.capstone.route_service.controller;

import com.capstone.route_service.entity.Route;
import com.capstone.route_service.entity.Stop;
import com.capstone.route_service.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/routes")
public class RouteController {

    @Autowired
    private RouteService routeService;

    @PostMapping
    public ResponseEntity<Route> createRoute(@RequestBody Route route) {
        Route createdRoute = routeService.createRoute(route);
        return ResponseEntity.ok(createdRoute);
    }

    @GetMapping
    public ResponseEntity<List<Route>> getAllRoutes() {
        return ResponseEntity.ok(routeService.getAllRoutes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Route> getRouteById(@PathVariable long id) {
        return routeService.getRouteById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{routeId}/stops")
    public ResponseEntity<Route> addStop(@PathVariable long routeId, @RequestBody Stop stop) {
        Route updatedRoute = routeService.addStopToRoute(routeId, stop);
        return updatedRoute != null ? ResponseEntity.ok(updatedRoute) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{routeId}/stops/{stopId}")
    public ResponseEntity<Route> removeStop(@PathVariable long routeId, @PathVariable long stopId) {
        Route updatedRoute = routeService.removeStopFromRoute(routeId, stopId);
        return updatedRoute != null ? ResponseEntity.ok(updatedRoute) : ResponseEntity.notFound().build();
    }

    @GetMapping("/find")
    public ResponseEntity<?> findRoutes(@RequestParam String source, @RequestParam String destination) {
        List<Long> routes = routeService.findRoutesBySourceAndDestination(source, destination);
        return ResponseEntity.ok(routes);
    }
}

