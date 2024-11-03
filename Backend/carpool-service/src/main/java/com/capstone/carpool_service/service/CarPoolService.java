package com.capstone.carpool_service.service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.carpool_service.entity.CarPool;
import com.capstone.carpool_service.entity.CarPoolUser;
import com.capstone.carpool_service.repository.CarPoolRepository;

@Service
public class CarPoolService {

	@Autowired
	private CarPoolRepository repository;

	public List<CarPool> getAllCarPool() {
		return repository.findAll();
	}

	public CarPool getCarPoolById(long id) {
		return repository.findById(id).orElse(null);
	}

	public CarPool addNewCarPool(CarPool car) {
		return repository.save(car);
	}

	public boolean deleteCar(long id) {
		repository.deleteById(id);
		return true;
	}

	public CarPool addCarPoolUser(long carPoolId, CarPoolUser user) {
		Optional<CarPool> car = repository.findById(carPoolId);
		if (car.isPresent()) {
			CarPool carPoolFound = car.get();
			user.setRequestTime(LocalDateTime.now());
			carPoolFound.getUsers().add(user);
			repository.save(carPoolFound);
			return carPoolFound;
		}
		return null;
	}

	public CarPool cancelCarPoolUserSeat(long carPoolId, long userId) {
		CarPool carPool = repository.findById(carPoolId).orElseThrow(() -> new RuntimeException("CarPool not found"));
		CarPoolUser poolUser = carPool.getUsers().stream().filter(user -> user.getId() == userId).findFirst()
				.orElseThrow(() -> new RuntimeException("User not found"));
		carPool.getUsers().remove(poolUser);
		
		repository.save(carPool);
		return carPool;
	}

	public boolean endJourney(long id) {
		CarPool car = repository.findById(id).get();
		if (car != null) {
			car.setStatus("Completed");
			repository.save(car);
			return true;
		}
		return false;
	}

}
