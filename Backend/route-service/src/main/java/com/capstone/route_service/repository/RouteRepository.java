package com.capstone.route_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capstone.route_service.entity.Route;

public interface RouteRepository extends JpaRepository<Route,Long>{
	List<Route> findByOriginAndDestination(String origin, String destination);
	
	@Query("SELECT r.id FROM Route r JOIN r.stops s1 JOIN r.stops s2 WHERE s1.name = :source AND s2.name = :destination")
    List<Integer> findRouteIdsBySourceAndDestination(@Param("source") String source, @Param("destination") String destination);
}
