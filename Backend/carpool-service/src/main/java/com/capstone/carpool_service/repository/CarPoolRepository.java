package com.capstone.carpool_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capstone.carpool_service.entity.CarPool;


@Repository
public interface CarPoolRepository extends JpaRepository<CarPool,Long>{

}
