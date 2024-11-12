package com.project.authentication_service.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import com.project.authentication_service.client.UserClient;
import com.project.authentication_service.entity.UserCredential;
import com.project.authentication_service.models.ChangePassworRequest;
import com.project.authentication_service.models.ForgotPasswordTokenPojo;
import com.project.authentication_service.models.ResetPassword;
import com.project.authentication_service.models.UserCredentialPojo;
import com.project.authentication_service.models.UserPojo;
import com.project.authentication_service.repository.RoleRepository;
import com.project.authentication_service.repository.UserCredentialRepository;
import com.project.authentication_service.service.UserCredentialService;

@RestController
@RequestMapping("/auth")
//@CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
public class UserCredentialController {

	@Autowired
	private UserCredentialService userCredentialService;
	
	@Autowired
	private UserCredentialRepository userRepo;
	
	@Autowired
	private RoleRepository roleRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserClient userClient;

	@GetMapping("/greet")
	public String greet() {
		return "Hello! From Authentication Service..............";
	}


	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody UserCredentialPojo user) {
		UserCredential userFound=userRepo.findByUsername(user.getUsername()).get();
		System.out.println(userFound.getId());
		Map<String, String> responseMap = new HashMap<>();
	    responseMap.put("token", userCredentialService.validateUser(user));
	    responseMap.put("role", userFound.getRoles().get(0).getName());
//		return new ResponseEntity<>(Map.entry("token", userCredentialService.validateUser(user)), HttpStatus.OK);
	    return new ResponseEntity<>(responseMap,HttpStatus.OK);
	}

	@PostMapping("/user/register")
	public ResponseEntity<?> registerNewUser(@RequestBody UserCredentialPojo user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return new ResponseEntity<>(userCredentialService.registerNewUser(user), HttpStatus.OK);
	}

	@GetMapping("/user/token/{token}")
	public ResponseEntity<?> getUserByToken(@PathVariable String token) {
		String username = userCredentialService.getUserId(token);
		RestClient restClient = RestClient.create();
//          UserPojo userPojo=restClient.get().uri("http://localhost:9000/users/email/"+username).retrieve().body(UserPojo.class);
		UserPojo userPojo = userClient.getUserByUsername(username);
		if (userPojo != null) {
			return new ResponseEntity<>(userPojo, HttpStatus.OK);
		}
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/forgot-password")
	public ResponseEntity<?> generateForgotPasswordLink(@RequestParam("username") String username) {
		ForgotPasswordTokenPojo pojo = userCredentialService.generateForgotPasswordToken(username);
		if (pojo != null) {
			return new ResponseEntity<>(pojo, HttpStatus.OK);
		}
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/verify/forgot-password")
	public ResponseEntity<?> verifyForgotPasswordToken(@RequestParam("token") String token) {
		String username = userCredentialService.verifyToken(token);
		if (username != null) {
			return new ResponseEntity<>(Map.entry("username", username), HttpStatus.OK);
		}
		return new ResponseEntity<>(Map.entry("message", "Request Token has expired"), HttpStatus.BAD_REQUEST);
	}

	@PostMapping("/reset/password")
	public ResponseEntity<?> resetPassword(@RequestParam("token") String token, @RequestBody ResetPassword resetPassword) {
		resetPassword.setPassword(passwordEncoder.encode(resetPassword.getPassword()));
		boolean succeed = userCredentialService.resetPassword(token,resetPassword);
		if (succeed) {
			return new ResponseEntity<>(Map.entry("message", "Reset Password Successfully"), HttpStatus.OK);
		}
		return new ResponseEntity<>(Map.entry("message", "Reset Password Failed , Link has been Expired"),
				HttpStatus.BAD_REQUEST);
	}

	
	@GetMapping("/validate/token")
	public ResponseEntity<?> validateJwtToken(@RequestParam("token") String token) {
		return new ResponseEntity<>(userCredentialService.validateToken(token), HttpStatus.OK);
	}
	
	@GetMapping("/admin/validate/token")
	public ResponseEntity<?> validateJwtTokenAndRole(@RequestParam("token") String token){
		return new ResponseEntity<>(userCredentialService.validateTokenAndRole(token),HttpStatus.OK);
	}
	
	@PutMapping("/change-password/{email}")
	public ResponseEntity<?> changePassword(@RequestBody ChangePassworRequest request,@PathVariable String email){
		boolean changed=userCredentialService.changePassword(email,request);
		if(!changed) {
			return new ResponseEntity<>(Map.entry("message", "Change Password Failed"),HttpStatus.OK);
		}
		return new ResponseEntity<>(Map.entry("message", "Password Change Successfull"),HttpStatus.OK);
	}

}
