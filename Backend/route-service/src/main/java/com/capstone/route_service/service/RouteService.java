package com.capstone.route_service.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.route_service.entity.Route;
import com.capstone.route_service.entity.Stop;
import com.capstone.route_service.repository.RouteRepository;
import com.capstone.route_service.repository.StopRepository;

@Service
public class RouteService {

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private StopRepository stopRepository;

    public Route addStopToRoute(long routeId, Stop stop) {
        Optional<Route> optionalRoute = routeRepository.findById(routeId);
        if (optionalRoute.isPresent()) {
            Route route = optionalRoute.get();
            stop.setRoute(route);
            route.getStops().add(stop);
            return routeRepository.save(route);
        }
        return null; 
    }

    public Route removeStopFromRoute(long routeId, long stopId) {
        Optional<Route> optionalRoute = routeRepository.findById(routeId);
        if (optionalRoute.isPresent()) {
            Route route = optionalRoute.get();
            Stop stopToRemove = null;
            for (Stop stop : route.getStops()) {
                if (stop.getId() == stopId) {
                    stopToRemove = stop;
                    break;
                }
            }
            if (stopToRemove != null) {
                route.getStops().remove(stopToRemove);
                return routeRepository.save(route);
            }
        }
        return null; 
    }

    public List<Long> findRoutesBySourceAndDestination(String source, String destination) {
        List<Route> routes = routeRepository.findByOriginAndDestination(source, destination);
        List<Long> routeIds = routes.stream().map(Route::getId).collect(Collectors.toList());

        
        List<Long> stopBasedRouteIds = routeRepository.findAll().stream()
            .filter(route -> route.getStops().stream().anyMatch(stop -> stop.getName().equals(source)) &&
                            route.getStops().stream().anyMatch(stop -> stop.getName().equals(destination)))
            .map(Route::getId)
            .collect(Collectors.toList());

        
        routeIds.addAll(stopBasedRouteIds);
        return routeIds.stream().distinct().collect(Collectors.toList());
    }

    public Route createRoute(Route route) {
        return routeRepository.save(route);
    }

    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }

    public Optional<Route> getRouteById(long id) {
        return routeRepository.findById(id);
    }
}
