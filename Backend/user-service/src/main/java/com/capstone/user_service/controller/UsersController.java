package com.capstone.user_service.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.user_service.entity.Users;
import com.capstone.user_service.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    @Autowired
    private UserService usersService;

    // Endpoint to register a new user
    @PostMapping("/register")
    public ResponseEntity<Users> register(@RequestBody Users user) {
        Users createdUser = usersService.registerUser(user);
        return ResponseEntity.ok(createdUser);
    }

    // Endpoint to get all users
    @GetMapping
    public ResponseEntity<List<Users>> getAllUsers() {
        List<Users> users = usersService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    // Endpoint to get a user by ID
    @GetMapping("/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable long id) {
        Optional<Users> user = usersService.getUserById(id);
        return user.map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }

    // Endpoint to update user details
    @PutMapping("/{id}")
    public ResponseEntity<Users> updateUser(@PathVariable long id, @RequestBody Users userDetails) {
        Users updatedUser = usersService.updateUser(id, userDetails);
        return ResponseEntity.ok(updatedUser);
    }

    // Endpoint to delete a user
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable long id) {
        usersService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
