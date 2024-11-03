package com.capstone.user_service.service;


import com.capstone.user_service.entity.Ticket;
import com.capstone.user_service.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    public Ticket bookTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public Optional<Ticket> getTicketById(long id) {
        return ticketRepository.findById(id);
    }

    public Ticket updateTicket(long id, Ticket ticketDetails) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));
        ticket.setBusId(ticketDetails.getBusId());
        ticket.setRouteId(ticketDetails.getRouteId());
        ticket.setDateOfJourney(ticketDetails.getDateOfJourney());
        ticket.setSeatNumber(ticketDetails.getSeatNumber());
        ticket.setPrice(ticketDetails.getPrice());
        ticket.setStatus(ticketDetails.getStatus());
        return ticketRepository.save(ticket);
    }

    public void cancelTicket(long id) {
        ticketRepository.deleteById(id);
    }
}