package com.capstone.user_service.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.user_service.client.AuthClient;
import com.capstone.user_service.models.RolePojo;
import com.capstone.user_service.models.UserCredentialPojo;
import com.capstone.user_service.models.UsersPojo;
import com.capstone.user_service.service.UserService;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private AuthClient authClient;

    @PostMapping
    public ResponseEntity<?> registerUser(@RequestBody UsersPojo userPojo) {
        UsersPojo createdUser = userService.registerUser(userPojo);
        UserCredentialPojo credentialPojo=new UserCredentialPojo();
        credentialPojo.setUsername(createdUser.getEmail());
        credentialPojo.setPassword(createdUser.getEmail().split("@")[0]);
        List<RolePojo> roles=new ArrayList<>();
        RolePojo pojoRole=new RolePojo();
        pojoRole.setName(createdUser.getRole());
        roles.add(pojoRole);
        credentialPojo.setRoles(roles);
        UserCredentialPojo authCreated=authClient.registerNewUser(credentialPojo);
        
        System.out.println(authCreated);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        List<UsersPojo> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable long id) {
        Optional<UsersPojo> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok)
                   .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable long id, @RequestBody UsersPojo userDetails) {
        UsersPojo updatedUser = userService.updateUser(id, userDetails);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(Map.entry("message", "Successfully Deleted the User"),HttpStatus.OK);
    }
    
    @GetMapping("/operators")
    public ResponseEntity<?> getAllOperators(){
    	return new ResponseEntity<>(userService.getAllOperators(),HttpStatus.OK);
    }
    
    @GetMapping("/user-count")
    public ResponseEntity<?> getAllUsersCount(){
    	return new ResponseEntity<>(userService.getCountOfUser(),HttpStatus.OK);
    }
    
    @GetMapping("/operator-count")
    public ResponseEntity<?> getAllOperatorsCount(){
    	return new ResponseEntity<>(userService.getAllOperators(),HttpStatus.OK);
    }
    
    @GetMapping("/registrations/{year}")
    public ResponseEntity<?> getMonthlyRegistrations(@PathVariable int year) {
        return new ResponseEntity<>(userService.getMonthlyUserRegistrations(year),HttpStatus.OK);
    }
    
    @GetMapping("/email/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email){
    	return new ResponseEntity<>(userService.getUserByEmail(email),HttpStatus.OK);
    }
}
