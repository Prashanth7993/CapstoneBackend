package com.capstone.user_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.capstone.user_service.entity.Users;

public interface UserRepository extends JpaRepository<Users, Long> {
	
	Users findByEmail(String email);

	List<Users> findAllByRole(String role);

	Long countByRole(String role);

	@Query("SELECT " + "  MONTH(u.createdDate) AS month, " + "  COUNT(u) AS userCount " + "FROM Users u "
			+ "WHERE YEAR(u.createdDate) = :year " + "GROUP BY MONTH(u.createdDate) " + "ORDER BY month")
	List<Object[]> getUserCountPerMonth(int year);
}
