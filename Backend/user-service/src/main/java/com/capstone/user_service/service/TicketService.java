package com.capstone.user_service.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.user_service.entity.Ticket;
import com.capstone.user_service.entity.Users;
import com.capstone.user_service.models.TicketPojo;
import com.capstone.user_service.repository.TicketRepository;
import com.capstone.user_service.repository.UserRepository;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;
    
    @Autowired
    private UserRepository userRepository;

    // Method to book a ticket
    public TicketPojo bookTicket(long userId,TicketPojo ticketPojo) {
    	Ticket ticket = new Ticket();
        BeanUtils.copyProperties(ticketPojo, ticket);
    	Users userFound=userRepository.findById(userId).get();
    	if(userFound!=null) {
    		ticket.setUser(userFound);
    	}
        Ticket savedTicket = ticketRepository.save(ticket);
        return convertToPojo(savedTicket);
    }

    // Method to get all tickets
    public List<TicketPojo> getAllTickets() {
        List<Ticket> tickets = ticketRepository.findAll();
        return tickets.stream()
                      .map(this::convertToPojo)
                      .collect(Collectors.toList());
    }

    // Method to get a ticket by ID
    public Optional<TicketPojo> getTicketById(long id) {
        return ticketRepository.findById(id)
                               .map(this::convertToPojo);
    }

    // Method to update ticket details
    public TicketPojo updateTicket(long id, TicketPojo ticketDetails) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));
        BeanUtils.copyProperties(ticketDetails, ticket, "id"); // Ignore id while copying
        Ticket updatedTicket = ticketRepository.save(ticket);
        return convertToPojo(updatedTicket);
    }

    // Method to cancel a ticket
    public void cancelTicket(long id) {
        ticketRepository.deleteById(id);
    }

    // Helper method to convert Ticket to TicketPojo
    private TicketPojo convertToPojo(Ticket ticket) {
        TicketPojo ticketPojo = new TicketPojo();
        BeanUtils.copyProperties(ticket, ticketPojo);
        return ticketPojo;
    }
}
