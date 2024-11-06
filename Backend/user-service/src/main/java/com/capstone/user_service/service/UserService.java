package com.capstone.user_service.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.user_service.entity.Ticket;
import com.capstone.user_service.entity.Users;
import com.capstone.user_service.models.TicketPojo;
import com.capstone.user_service.models.UsersPojo;
import com.capstone.user_service.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository usersRepository;

    // Method to register a new user
    public UsersPojo registerUser(UsersPojo userPojo) {
        Users user = new Users();
        BeanUtils.copyProperties(userPojo, user);
        Users savedUser = usersRepository.save(user);
        return convertToPojo(savedUser);
    }

    // Method to get all users
    public List<UsersPojo> getAllUsers() {
        List<Users> users = usersRepository.findAll();
        return users.stream()
                    .map(this::convertToPojo)
                    .collect(Collectors.toList());
    }

    // Method to get a user by ID
    public Optional<UsersPojo> getUserById(long id) {
        return usersRepository.findById(id)
                              .map(this::convertToPojo);
    }

    // Method to update user details
    public UsersPojo updateUser(long id, UsersPojo userDetails) {
        Users user = usersRepository.findById(id)
                                     .orElseThrow(() -> new RuntimeException("User not found"));
        BeanUtils.copyProperties(userDetails, user, "id"); // Ignore id while copying
        Users updatedUser = usersRepository.save(user);
        return convertToPojo(updatedUser);
    }

    // Method to delete a user
    public void deleteUser(long id) {
        usersRepository.deleteById(id);
    }

    // Helper method to convert Users to UsersPojo
    private UsersPojo convertToPojo(Users user) {
        UsersPojo usersPojo = new UsersPojo();
        BeanUtils.copyProperties(user, usersPojo);
        List<Ticket> ticketsFound=user.getTickets();
        List<TicketPojo> tickets=new ArrayList<>();
        ticketsFound.stream().forEach(ticketFound->{
        	TicketPojo pojoTicket=new TicketPojo();
        	BeanUtils.copyProperties(ticketFound, pojoTicket);
        	tickets.add(pojoTicket);
        });
        usersPojo.setTickets(tickets);
        return usersPojo;
    }
}
