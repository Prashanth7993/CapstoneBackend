package com.capstone.carpool_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.capstone.carpool_service.models.TransactionPojo;

@FeignClient(value="payment-service",url="http://localhost:9696/payment")
public interface PaymentClient {

	@PostMapping("/create-order")
	public TransactionPojo createNewTransaction(@RequestBody TransactionPojo transactionPojo)
			throws Exception;
			
}
