package com.capstone.user_service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.user_service.entity.Users;
import com.capstone.user_service.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository usersRepository;

    // Method to register a new user
    public Users registerUser(Users user) {
        return usersRepository.save(user);
    }

    // Method to get all users
    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    // Method to get a user by ID
    public Optional<Users> getUserById(long id) {
        return usersRepository.findById(id);
    }

    // Method to update user details
    public Users updateUser(long id, Users userDetails) {
        Users user = usersRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setName(userDetails.getName());
        user.setEmail(userDetails.getEmail());
        user.setPhone(userDetails.getPhone());
        user.setRole(userDetails.getRole());
        return usersRepository.save(user);
    }

    // Method to delete a user
    public void deleteUser(long id) {
        usersRepository.deleteById(id);
    }
}
