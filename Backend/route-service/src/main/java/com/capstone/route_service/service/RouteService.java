package com.capstone.route_service.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.route_service.entity.RailwayStation;
import com.capstone.route_service.entity.Route;
import com.capstone.route_service.entity.Stop;
import com.capstone.route_service.models.RailwayStationPojo;
import com.capstone.route_service.models.RoutePojo;
import com.capstone.route_service.models.StopPojo;
import com.capstone.route_service.repository.RouteRepository;
import com.capstone.route_service.repository.StopRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class RouteService {

	@Autowired
	private RouteRepository routeRepository;

	@Autowired
	private StopRepository stopRepository;

	public RoutePojo addStopToRoute(long routeId, StopPojo stopPojo) {
		Optional<Route> optionalRoute = routeRepository.findById(routeId);
		if (optionalRoute.isPresent()) {
			Route route = optionalRoute.get();
			Stop stop = new Stop();
			BeanUtils.copyProperties(stopPojo, stop);
			stop.setRoute(route);
			route.getStops().add(stop);
			Route updatedRoute = routeRepository.save(route);
			return convertToPojo(updatedRoute);
		}
		return null;
	}

	public RoutePojo removeStopFromRoute(long routeId, long stopId) {
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
				Route updatedRoute = routeRepository.save(route);
				return convertToPojo(updatedRoute);
			}
		}
		return null;
	}

	public List<Long> findRoutesBySourceAndDestination(String source, String destination) {
		List<Route> routes = routeRepository.findByOriginAndDestination(source, destination);
		List<Long> routeIds = routes.stream().map(Route::getId).collect(Collectors.toList());

		List<Long> stopBasedRouteIds = routeRepository.findAll().stream()
				.filter(route -> route.getStops().stream().anyMatch(stop -> stop.getName().equals(source))
						&& route.getStops().stream().anyMatch(stop -> stop.getName().equals(destination)))
				.map(Route::getId).collect(Collectors.toList());

		routeIds.addAll(stopBasedRouteIds);
		return routeIds.stream().distinct().collect(Collectors.toList());
	}

	public RoutePojo createRoute(RoutePojo routePojo) {
		System.out.println(routePojo);
		Route route = new Route();
		BeanUtils.copyProperties(routePojo, route);
//        Stop source = new Stop();
//        Stop destination = new Stop();
//        source.setName(routePojo.getOrigin());
//        destination.setName(routePojo.getDestination());
//        List<Stop> stops = new ArrayList<>();
//        stops.add(source);
//        stops.add(destination);
//        route.setStops(stops);
		StopPojo sourcePojo = new StopPojo();
		StopPojo destinationPojo = new StopPojo();
		sourcePojo.setName(routePojo.getOrigin());
		destinationPojo.setName(routePojo.getDestination());
		// Create route first
		Route savedRoute = routeRepository.save(route);
		// then add source and destination as stops
		addStopToRoute(savedRoute.getId(), sourcePojo);
		RoutePojo updatedRoute = addStopToRoute(savedRoute.getId(), destinationPojo);
		return updatedRoute;
	}

	public List<RoutePojo> getAllRoutes() {
		List<Route> routes = routeRepository.findAll();
		return routes.stream().map(this::convertToPojo).collect(Collectors.toList());
	}

	public Optional<RoutePojo> getRouteById(long id) {
		return routeRepository.findById(id).map(this::convertToPojo);
	}

	// Helper method to convert Route to RoutePojo
	public RoutePojo convertToPojo(Route route) {
		RoutePojo routePojo = new RoutePojo();
		BeanUtils.copyProperties(route, routePojo);
		List<Stop> stops = route.getStops();
		List<RailwayStation> stations = route.getStations();
		List<StopPojo> pojoStops = new ArrayList<>();
		if (stops.size() > 0 || stops != null) {
			stops.stream().forEach(stop -> {
				StopPojo pojoStop = new StopPojo();
				BeanUtils.copyProperties(stop, pojoStop);
				pojoStops.add(pojoStop);
			});
		}
		List<RailwayStationPojo> stationsPojo = new ArrayList<>();
		if (stations.size() > 0) {
			stations.stream().forEach(station -> {
				RailwayStationPojo pojoStation = new RailwayStationPojo();
				BeanUtils.copyProperties(station, pojoStation);
				stationsPojo.add(pojoStation);
			});
		}
		routePojo.setStops(pojoStops);
		routePojo.setStations(stationsPojo);
		return routePojo;
	}

	public RoutePojo addStationToRoute(long routeId, RailwayStationPojo stationPojo) {

		Route route = routeRepository.findById(routeId)
				.orElseThrow(() -> new EntityNotFoundException("Route not found with id: " + routeId));

		RailwayStation railwayStation = new RailwayStation();
		BeanUtils.copyProperties(stationPojo, railwayStation);

		railwayStation.setRoute(route);
		route.getStations().add(railwayStation);

		Route updatedRoute = routeRepository.saveAndFlush(route);

		RoutePojo routePojo = new RoutePojo();
		BeanUtils.copyProperties(updatedRoute, routePojo, "stations"); // Exclude stations from direct copying

		List<RailwayStationPojo> stationPojos = new ArrayList<>();
		for (RailwayStation station : updatedRoute.getStations()) {
			RailwayStationPojo stationDto = new RailwayStationPojo();
			BeanUtils.copyProperties(station, stationDto);
			stationPojos.add(stationDto);
		}
		routePojo.getStations().add(stationPojo);

		return routePojo;

	}

	public List<RailwayStationPojo> getAllStationsOfRoute(long routeId) {
		Route routeFound = routeRepository.findById(routeId).get();
		List<RailwayStation> stationsFound = routeFound.getStations();
		List<RailwayStationPojo> stations = new ArrayList<>();
		if (routeFound != null && stationsFound.size() > 0) {
			stationsFound.stream().forEach(station -> {
				RailwayStationPojo pojoStation = new RailwayStationPojo();
				BeanUtils.copyProperties(station, pojoStation);
				stations.add(pojoStation);
			});
		}
		return stations;
	}
}
