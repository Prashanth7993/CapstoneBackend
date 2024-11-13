package com.capstone.carpool_service.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.carpool_service.client.NotificationClient;
import com.capstone.carpool_service.client.PaymentClient;
import com.capstone.carpool_service.models.CarPoolPojo;
import com.capstone.carpool_service.models.NotificationPojo;
import com.capstone.carpool_service.models.TransactionPojo;
import com.capstone.carpool_service.service.CarPoolService;

@RestController
@RequestMapping("/carpools")
public class CarPoolController {

	private final CarPoolService carpoolService;

	public CarPoolController(CarPoolService carpoolService) {
		this.carpoolService = carpoolService;
	}

	@Autowired
	NotificationClient notificationClient;

	@Autowired
	PaymentClient paymentClient;

	@GetMapping
	public ResponseEntity<?> getAllCarPools() {
		return new ResponseEntity<>(carpoolService.getAllCarpools(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getCarPoolById(@PathVariable long id) {
		return new ResponseEntity<>(carpoolService.getCarpool(id), HttpStatus.OK);
	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<?> getCarpoolsByUserId(@PathVariable long userId) {
		return new ResponseEntity<>(carpoolService.getAllCarpoolsByUserId(userId), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> createCarpool(@RequestBody CarPoolPojo carpool) {
		NotificationPojo notification = new NotificationPojo();
		notification.setMessage("Successfully Created CarPool, You Can Now Accept Reservations");
		notification.setUserId(carpool.getDriverId());
		notification.setRead(false);
		notification.setType("Informational");
		NotificationPojo notified = notificationClient.addNewNotificationToUser(carpool.getDriverId(), notification);
		return ResponseEntity.ok(carpoolService.createCarpool(carpool));
	}

	@PostMapping("/{carpoolId}/users/{userId}")
	public ResponseEntity<?> addUserToCarpool(@PathVariable Long carpoolId, @PathVariable Long userId)
			throws Exception {
		CarPoolPojo foundCar = carpoolService.getCarpool(carpoolId);
		CarPoolPojo pojoCar = carpoolService.addUserToCarpool(carpoolId, userId);
		NotificationPojo notification = new NotificationPojo();
		notification.setMessage("Hurrayyyyy.......Seat Reservation Success.....!!");
		notification.setUserId(userId);
		notification.setRead(false);
		notification.setType("Informational");
		NotificationPojo notified = notificationClient.addNewNotificationToUser(userId, notification);
		Map<String, Object> responseMap = new HashMap<>();
		responseMap.put("reservation", pojoCar);
		TransactionPojo transactionPojo = new TransactionPojo();
		transactionPojo.setAmount(foundCar.getPrice() * 100);
		transactionPojo.setCurrency("INR");
		transactionPojo.setStatus("Created");
		transactionPojo.setUserId(userId);
		TransactionPojo transaction = paymentClient.createNewTransaction(transactionPojo);
		responseMap.put("paymentTransaction", transaction);
		return new ResponseEntity<>(responseMap, HttpStatus.OK);
	}

	@DeleteMapping("/{carpoolId}/users/{userId}")
	public ResponseEntity<?> removeUserFromCarpool(@PathVariable Long carpoolId, @PathVariable Long userId) {
		carpoolService.removeUserFromCarpool(carpoolId, userId);
		NotificationPojo notification = new NotificationPojo();
		notification.setMessage("Your Seat Reservation has Been Cancelled");
		notification.setUserId(userId);
		notification.setRead(false);
		notification.setType("Informational");
		NotificationPojo notified = notificationClient.addNewNotificationToUser(userId, notification);
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
		CarPoolPojo pojo = carpoolService.getCarpool(id);
		NotificationPojo notification = new NotificationPojo();
		notification.setMessage("You have deleted your CarPool Service...");
		notification.setUserId(pojo.getDriverId());
		notification.setRead(false);
		notification.setType("Informational");
		NotificationPojo notified = notificationClient.addNewNotificationToUser(pojo.getDriverId(), notification);
		return new ResponseEntity<>(carpoolService.deleteCarPool(id), HttpStatus.OK);
	}

	@GetMapping("/recent-bookings/{userId}")
	public ResponseEntity<?> getRecentBookings(@PathVariable long userId) {
		return new ResponseEntity<>(carpoolService.getRecentBookingsOfUser(userId), HttpStatus.OK);
	}

	@GetMapping("/bookings-count")
	public ResponseEntity<?> getBookingsCountTillDate() {
		return new ResponseEntity<>(carpoolService.getAllBookingsTillDate(), HttpStatus.OK);
	}
}