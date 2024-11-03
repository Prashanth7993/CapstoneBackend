package com.capstone.bus_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capstone.bus_service.entity.BoardingDeboardingLog;

public interface BoardingDeboardingLogRepository extends JpaRepository<BoardingDeboardingLog, Long> {

}
