package com.capstone.user_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capstone.user_service.entity.Users;

public interface UserRepository extends JpaRepository<Users,Long> {
	 Users findByEmail(String email);
}
