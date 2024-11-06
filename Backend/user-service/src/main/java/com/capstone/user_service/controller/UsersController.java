package com.capstone.user_service.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.capstone.user_service.models.UsersPojo;
import com.capstone.user_service.service.UserService;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> registerUser(@RequestBody UsersPojo userPojo) {
        UsersPojo createdUser = userService.registerUser(userPojo);
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
        return new ResponseEntity<>("Success",HttpStatus.OK);
    }
}
