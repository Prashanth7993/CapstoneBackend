package com.capstone.user_service.controller;

import com.capstone.user_service.models.TicketPojo;
import com.capstone.user_service.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping("/{userId}")
    public ResponseEntity<TicketPojo> bookTicket(@PathVariable long userId,@RequestBody TicketPojo ticketPojo) {
        TicketPojo createdTicket = ticketService.bookTicket(userId,ticketPojo);
        return ResponseEntity.ok(createdTicket);
    }

    
    @GetMapping
    public ResponseEntity<List<TicketPojo>> getAllTickets() {
        List<TicketPojo> tickets = ticketService.getAllTickets();
        return ResponseEntity.ok(tickets);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketPojo> getTicketById(@PathVariable long id) {
        Optional<TicketPojo> ticket = ticketService.getTicketById(id);
        return ticket.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.notFound().build());
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<TicketPojo> updateTicket(@PathVariable long id, @RequestBody TicketPojo ticketDetails) {
        TicketPojo updatedTicket = ticketService.updateTicket(id, ticketDetails);
        return ResponseEntity.ok(updatedTicket);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelTicket(@PathVariable long id) {
        ticketService.cancelTicket(id);
        return ResponseEntity.noContent().build();
    }
}
