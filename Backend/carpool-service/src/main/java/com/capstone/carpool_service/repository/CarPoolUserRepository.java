package com.capstone.carpool_service.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.capstone.carpool_service.entity.CarPoolUser;

public interface CarPoolUserRepository extends JpaRepository<CarPoolUser, Long> {
    List<CarPoolUser> findByCarpoolId(Long carpoolId);
    List<CarPoolUser> findByUserId(Long userId);
    
    
}
