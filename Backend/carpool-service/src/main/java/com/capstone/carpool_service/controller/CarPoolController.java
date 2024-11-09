package com.capstone.carpool_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.carpool_service.models.CarPoolPojo;
import com.capstone.carpool_service.service.CarPoolService;

@RestController
@RequestMapping("/carpools")
public class CarPoolController {

	private final CarPoolService carpoolService;

	public CarPoolController(CarPoolService carpoolService) {
		this.carpoolService = carpoolService;
	}

	@GetMapping
	public ResponseEntity<?> getAllCarPools() {
		return new ResponseEntity<>(carpoolService.getAllCarpools(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getCarPoolById(@PathVariable long id) {
		return new ResponseEntity<>(carpoolService.getCarpool(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> createCarpool(@RequestBody CarPoolPojo carpool) {
		return ResponseEntity.ok(carpoolService.createCarpool(carpool));
	}

	@PostMapping("/{carpoolId}/users/{userId}")
	public ResponseEntity<?> addUserToCarpool(@PathVariable Long carpoolId, @PathVariable Long userId) {
		CarPoolPojo pojoCar = carpoolService.addUserToCarpool(carpoolId, userId);
		return new ResponseEntity<>(pojoCar, HttpStatus.OK);
	}

	@DeleteMapping("/{carpoolId}/users/{userId}")
	public ResponseEntity<?> removeUserFromCarpool(@PathVariable Long carpoolId, @PathVariable Long userId) {
		carpoolService.removeUserFromCarpool(carpoolId, userId);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{carpoolId}/users")
	public ResponseEntity<?> getCarpoolUsers(@PathVariable Long carpoolId) {
		return ResponseEntity.ok(carpoolService.findCarpoolUsers(carpoolId));
	}

	@GetMapping("/route/{routeId}")
	public ResponseEntity<?> getCarPoolsByRouteId(@PathVariable long routeId) {
		return new ResponseEntity<>(carpoolService.getCarPoolsByRoute(routeId), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCarPoolById(@PathVariable long id) {
		return new ResponseEntity<>(carpoolService.deleteCarPool(id), HttpStatus.OK);
	}

	@GetMapping("/bookings-count")
	public ResponseEntity<?> getBookingsCountTillDate() {
		return new ResponseEntity<>(carpoolService.getAllBookingsTillDate(), HttpStatus.OK);
	}
}