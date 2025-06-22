package com.capstone.user_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.capstone.user_service.models.UserCredentialPojo;


@FeignClient(value="authentication-service",url="http://api-gateway:9000/auth")
public interface AuthClient {
	
	@PostMapping("/user/register")
	public UserCredentialPojo registerNewUser(@RequestBody UserCredentialPojo user) ;

}
