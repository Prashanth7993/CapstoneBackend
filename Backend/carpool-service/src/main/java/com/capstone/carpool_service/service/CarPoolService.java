package com.capstone.carpool_service.service;

import java.beans.PropertyDescriptor;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.carpool_service.entity.CarPool;
import com.capstone.carpool_service.entity.CarPoolUser;
import com.capstone.carpool_service.models.CarPoolPojo;
import com.capstone.carpool_service.models.CarPoolUserPojo;
import com.capstone.carpool_service.repository.CarPoolRepository;
import com.capstone.carpool_service.repository.CarPoolUserRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class CarPoolService {

	@Autowired
	private CarPoolRepository carpoolRepository;

	@Autowired
	private CarPoolUserRepository carpoolUserRepository;

	public List<CarPoolPojo> getAllCarpools() {
		List<CarPool> carpoolsFound = carpoolRepository.findAll();
		List<CarPoolPojo> carpools = new ArrayList<>();

		carpoolsFound.forEach(carpool -> {
			// Create and map the main carpool object
			CarPoolPojo carpoolPojo = new CarPoolPojo();
			BeanUtils.copyProperties(carpool, carpoolPojo);

			// Map the nested carpool users
			Set<CarPoolUserPojo> userPojos = new HashSet<>();
			if (carpool.getCarpoolUsers() != null) {
				carpool.getCarpoolUsers().forEach(user -> {
					CarPoolUserPojo userPojo = new CarPoolUserPojo();
					BeanUtils.copyProperties(user, userPojo);
					userPojos.add(userPojo);
				});
			}
			carpoolPojo.setCarpoolUsers(userPojos);
			carpools.add(carpoolPojo);
		});

		return carpools;
	}

	public CarPoolPojo getCarpool(Long id) {
		CarPool carpool = carpoolRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Carpool not found"));

		CarPoolPojo carpoolPojo = new CarPoolPojo();
		BeanUtils.copyProperties(carpool, carpoolPojo);

		// Map nested users
		Set<CarPoolUserPojo> userPojos = new HashSet<>();
		carpool.getCarpoolUsers().forEach(user -> {
			CarPoolUserPojo userPojo = new CarPoolUserPojo();
			BeanUtils.copyProperties(user, userPojo);
			userPojos.add(userPojo);
		});
		carpoolPojo.setCarpoolUsers(userPojos);

		return carpoolPojo;
	}

	public CarPoolPojo createCarpool(CarPoolPojo carpoolPojo) {
		try {
			CarPool carpool = new CarPool();
			BeanUtils.copyProperties(carpoolPojo, carpool);
			carpool.setAvailableSeats(carpool.getCapacity());

			CarPool savedCarpool = carpoolRepository.save(carpool);

			CarPoolPojo savedPojo = new CarPoolPojo();
			BeanUtils.copyProperties(savedCarpool, savedPojo);
			return savedPojo;

		} catch (Exception e) {
			System.out.println("Error creating carpool: " + e);
			throw new RuntimeException("Failed to create carpool", e);
		}
	}

	public CarPoolPojo updateCarpool(Long id, CarPoolPojo carpoolPojo) {
		CarPool existingCarpool = carpoolRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Carpool not found"));

		// Only copy non-null properties
		BeanUtils.copyProperties(carpoolPojo, existingCarpool, getNullPropertyNames(carpoolPojo));

		CarPool updatedCarpool = carpoolRepository.save(existingCarpool);
		CarPoolPojo updatedPojo = new CarPoolPojo();
		BeanUtils.copyProperties(updatedCarpool, updatedPojo);

		return updatedPojo;
	}

	// Utility method to get null property names
	private String[] getNullPropertyNames(Object source) {
		final BeanWrapper src = new BeanWrapperImpl(source);
		PropertyDescriptor[] pds = src.getPropertyDescriptors();

		Set<String> emptyNames = new HashSet<>();
		for (PropertyDescriptor pd : pds) {
			Object srcValue = src.getPropertyValue(pd.getName());
			if (srcValue == null) {
				emptyNames.add(pd.getName());
			}
		}

		String[] result = new String[emptyNames.size()];
		return emptyNames.toArray(result);
	}

	public CarPoolPojo addUserToCarpool(Long carpoolId, Long userId) {
		CarPool carpool = carpoolRepository.findById(carpoolId)
				.orElseThrow(() -> new EntityNotFoundException("Carpool not found"));

		if (carpool.getAvailableSeats() <= 0) {
			throw new IllegalStateException("No available seats");
		}

		CarPoolUser carpoolUser = new CarPoolUser();
		carpoolUser.setUserId(userId);
		carpoolUser.setRequestTime(LocalTime.now());
		carpoolUser.setCarpool(carpool);

		carpool.getCarpoolUsers().add(carpoolUser);
		carpool.setAvailableSeats(carpool.getAvailableSeats() - 1);
		CarPoolPojo pojoCar=new CarPoolPojo();
		BeanUtils.copyProperties(carpool, pojoCar);
		Set<CarPoolUserPojo> usersPojo=new HashSet<>();
		Set<CarPoolUser> users=carpool.getCarpoolUsers();
		users.stream().forEach(user->{
			CarPoolUserPojo userPojo=new CarPoolUserPojo();
			BeanUtils.copyProperties(user, userPojo);
			usersPojo.add(userPojo);
		});
		pojoCar.setCarpoolUsers(usersPojo);
		carpoolRepository.save(carpool);
		return pojoCar;
	}

	public void removeUserFromCarpool(Long carpoolId, Long userId) {
		CarPool carpool = carpoolRepository.findById(carpoolId)
				.orElseThrow(() -> new EntityNotFoundException("Carpool not found"));

		CarPoolUser carpoolUser = carpool.getCarpoolUsers().stream().filter(cu -> cu.getUserId().equals(userId))
				.findFirst().orElseThrow(() -> new EntityNotFoundException("User not found in carpool"));

		carpool.getCarpoolUsers().remove(carpoolUser);
		carpoolUserRepository.delete(carpoolUser);
	}

	public List<CarPoolUser> findCarpoolUsers(Long carpoolId) {
		return carpoolUserRepository.findByCarpoolId(carpoolId);
	}
	
	public List<CarPoolUserPojo> getRecentBookingsOfUser(long userId){
		System.out.println("Service");
		List<CarPoolUser> userList=carpoolUserRepository.findByUserId(userId);
//		System.out.println(userList);
		List<CarPoolUserPojo> listOfReservations=new ArrayList<>();
		userList.stream().forEach(user->{
			CarPoolUserPojo pojo=new CarPoolUserPojo();
			CarPoolPojo carPoolPojo=new CarPoolPojo();
			BeanUtils.copyProperties(user, pojo);
			BeanUtils.copyProperties(user.getCarpool(), carPoolPojo);
			pojo.setCarpool(carPoolPojo);
			listOfReservations.add(pojo);
		});
		
		return listOfReservations;
	}
	
	public List<CarPoolPojo> getCarPoolsByRoute(long routeId){
		List<CarPool> carPoolsFound=carpoolRepository.findByRouteId(routeId);
		List<CarPoolPojo> carPoolPojo=new ArrayList<>();
		carPoolsFound.stream().forEach(carPool->{
			CarPoolPojo pojoCarPool=new CarPoolPojo();
			BeanUtils.copyProperties(carPool, pojoCarPool);
			carPoolPojo.add(pojoCarPool);
		});
		return carPoolPojo;
	}

	public boolean deleteCarPool(long id) {
		carpoolRepository.deleteById(id);
		return true;
	}
	
	public long getAllBookingsTillDate() {
		return carpoolUserRepository.count();
	}
}