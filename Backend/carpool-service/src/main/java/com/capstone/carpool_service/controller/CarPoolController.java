package com.capstone.carpool_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.carpool_service.entity.CarPool;
import com.capstone.carpool_service.entity.CarPoolUser;
import com.capstone.carpool_service.service.CarPoolService;

@RestController
@RequestMapping("/car-pool")
public class CarPoolController {
	
	@Autowired
	private CarPoolService carService;
	
	@GetMapping
	public ResponseEntity<?> getAllCarPoolsAvailable(){
		return new ResponseEntity<>(carService.getAllCarPool(),HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getCarPoolById(long id){
		CarPool car=carService.getCarPoolById(id);
		if(car!=null) {
			return new ResponseEntity<>(car,HttpStatus.OK);
		}
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping
	public ResponseEntity<?> addCarPool(CarPool carPool){
		return new ResponseEntity<>(carService.addNewCarPool(carPool),HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCarPool(long id){
		boolean deleted=carService.deleteCar(id);
		if(deleted) {
			return new ResponseEntity<>(deleted,HttpStatus.OK);
		}
		return new ResponseEntity<>(deleted,HttpStatus.BAD_REQUEST);
	}
	
	
	@PostMapping("/reserve-seat/{carPoolId}")
	public ResponseEntity<?> reserveCarPoolSeat(long carPoolId,CarPoolUser carPoolUser){
		CarPool carPool=carService.addCarPoolUser(carPoolId, carPoolUser);
		if(carPool!=null) {
			return new ResponseEntity<>(carPool,HttpStatus.OK);
		}
		return ResponseEntity.badRequest().build();
	}
	
	@DeleteMapping("/cancel-seat/{carPoolId}/user/{userId}")
	public ResponseEntity<?> removeUserFromCarPool(
            @PathVariable long carPoolId,
            @PathVariable long userId) {
        try {
            CarPool carPool=carService.cancelCarPoolUserSeat(carPoolId, userId);
            return new ResponseEntity<>(carPool,HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
	
	
	@DeleteMapping("/end-service/{id}")
	public ResponseEntity<?> endJourney(long id){
		boolean ended=carService.endJourney(id);
		if(ended) {
			return new ResponseEntity<>(ended,HttpStatus.OK);
		}
		return new ResponseEntity<>(ended,HttpStatus.BAD_REQUEST);	
	}
	
	

}
