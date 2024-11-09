package com.capstone.bus_service.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @Column(name = "route_id")
    private long routeId;
    
    private long capacity;
    private String status;

    @OneToMany(mappedBy = "bus", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<RealTimeData> realTimeData;
    
    @OneToMany(mappedBy = "bus")
    @JsonIgnore
    private List<BusSchedule> busSchedules;

	@Override
	public String toString() {
		return "Bus [id=" + id + ", routeId=" + routeId + ", capacity=" + capacity + ", status=" + status
				+ ", realTimeData=" + realTimeData + ", busSchedules=" + busSchedules + "]";
	}
    
    
    
}