package com.capstone.bus_service.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class BusSchedule {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long routeId;

    private LocalDateTime departureTime;
    private LocalDateTime destinationArrivalTime;
    private boolean operatingStatus;

    @ManyToOne
    @JoinColumn(name = "bus_id")
    @JsonBackReference
    private Bus bus;

	@Override
	public String toString() {
		return "BusSchedule [id=" + id + ", routeId=" + routeId + ", departureTime=" + departureTime
				+ ", destinationArrivalTime=" + destinationArrivalTime + ", operatingStatus=" + operatingStatus + "]";
	}
    
}
