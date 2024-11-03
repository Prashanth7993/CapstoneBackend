package com.capstone.user_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capstone.user_service.entity.Ticket;

public interface TicketRepository extends JpaRepository<Ticket,Long> {

}
